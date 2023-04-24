package org.phyloviz.pwp.compute.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.ToolTemplateRepository;
import org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.ToolTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.ToolTemplateData;
import org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.access.AccessTypeTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_instances.WorkflowInstanceRepository;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_instances.documents.WorkflowInstance;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_instances.documents.WorkflowStatus;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.WorkflowTemplateRepository;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents.TaskTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents.WorkflowTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents.WorkflowTemplateData;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents.arguments.WorkflowTemplateArgumentProperties;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents.arguments.WorkflowTemplateArguments;
import org.phyloviz.pwp.compute.service.dtos.create_workflow.CreateWorkflowOutput;
import org.phyloviz.pwp.compute.service.dtos.get_workflow.GetWorkflowStatusOutput;
import org.phyloviz.pwp.compute.service.exceptions.DatasetDoesNotExistException;
import org.phyloviz.pwp.compute.service.exceptions.DistanceMatrixDoesNotExistException;
import org.phyloviz.pwp.compute.service.exceptions.InvalidWorkflowException;
import org.phyloviz.pwp.compute.service.exceptions.TemplateNotFound;
import org.phyloviz.pwp.compute.service.exceptions.TreeDoesNotExistException;
import org.phyloviz.pwp.compute.service.exceptions.TreeViewDoesNotExistException;
import org.phyloviz.pwp.compute.service.exceptions.WorkflowInstanceNotFoundException;
import org.phyloviz.pwp.compute.service.flowviz.FLOWViZClient;
import org.phyloviz.pwp.compute.service.flowviz.exceptions.UnexpectedResponseException;
import org.phyloviz.pwp.compute.service.flowviz.models.get_workflow.AirflowWorkflowStatus;
import org.phyloviz.pwp.compute.service.flowviz.models.get_workflow.GetWorkflowResponse;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.Tool;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.Workflow;
import org.phyloviz.pwp.compute.utils.UUIDUtils;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link ComputeService} interface.
 */
@Service
@RequiredArgsConstructor
public class ComputeServiceImpl implements ComputeService {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;

    private final DistanceMatrixMetadataRepository distanceMatrixMetadataRepository;
    private final TreeMetadataRepository treeMetadataRepository;
    private final TreeViewMetadataRepository treeViewMetadataRepository;

    private final WorkflowTemplateRepository workflowTemplateRepository;
    private final WorkflowInstanceRepository workflowInstanceRepository;
    private final ToolTemplateRepository toolTemplateRepository;
    private final FLOWViZClient flowVizClient;

    @Override
    public CreateWorkflowOutput createWorkflow(
            String projectId,
            String workflowType,
            Map<String, String> workflowProperties,
            String userId
    ) {
        validateUUIDArgument(projectId, "projectId");

        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        return createWorkflow(projectId, workflowType, workflowProperties);
    }

    @Override
    public GetWorkflowStatusOutput getWorkflowStatus(String projectId, String workflowId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        WorkflowInstance workflowInstance = workflowInstanceRepository
                .findById(workflowId)
                .orElseThrow(WorkflowInstanceNotFoundException::new);

        return getWorkflowStatusOutput(workflowInstance);
    }

    @Override
    public List<GetWorkflowStatusOutput> getAllWorkflows(String projectId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        return workflowInstanceRepository.findAllByProjectId(projectId).stream()
                .map(this::getWorkflowStatusOutput).toList();
    }

    @Override
    public List<GetWorkflowStatusOutput> getAllRunningWorkflows(String projectId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        return workflowInstanceRepository.findAllByProjectIdAndRunning(projectId).stream()
                .map(this::getWorkflowStatusOutput)
                .filter(w -> w.getStatus() == WorkflowStatus.RUNNING).toList();
    }

    @Override
    public List<GetWorkflowStatusOutput> getAllNotRunningWorkflows(String projectId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        return workflowInstanceRepository.findAllByProjectIdAndNotRunning(projectId).stream()
                .map(this::getWorkflowStatusOutput).toList();
    }

    private GetWorkflowStatusOutput getWorkflowStatusOutput(WorkflowInstance workflowInstance) {
        if (workflowInstance.getStatus() == WorkflowStatus.RUNNING) {
            workflowInstance.setStatus(getWorkflowStatusFromFLOWViZ(workflowInstance));
            workflowInstanceRepository.save(workflowInstance);
        }
        return new GetWorkflowStatusOutput(
                workflowInstance.getId(),
                workflowInstance.getType(),
                workflowInstance.getStatus(),
                workflowInstance.getData()
        );
    }

    private WorkflowStatus getWorkflowStatusFromFLOWViZ(WorkflowInstance workflowInstance) {
        GetWorkflowResponse workflow;
        try {
            workflow = flowVizClient.workflowService().getWorkflow(workflowInstance.getWorkflow().getName());
        } catch (UnexpectedResponseException e) {
            if (e.getResponse().code() == 404) {
                return WorkflowStatus.RUNNING;
            }
            throw e;
        }

        List<AirflowWorkflowStatus> workflowRuns = workflow.getAirflow().getRuns();

        if (workflowRuns.isEmpty())
            return WorkflowStatus.RUNNING;

        AirflowWorkflowStatus airflowWorkflowStatus = workflowRuns.get(0);

        String airflowStatus = airflowWorkflowStatus.getState();

        return switch (airflowStatus) {
            case "running" -> WorkflowStatus.RUNNING;
            case "success" -> WorkflowStatus.SUCCESS;
            case "failed" -> WorkflowStatus.FAILED;
            default -> throw new IllegalStateException("Unexpected value: " + airflowStatus);
        };
    }

    private CreateWorkflowOutput createWorkflow(String projectId, String workflowType, Map<String, String> properties) {
        //TODO: Fix transactions

        // Maybe we should only retrieve the workflow template on startup?
        WorkflowTemplate workflowTemplate = workflowTemplateRepository
                .findByName(workflowType)
                .orElseThrow(() -> new InvalidWorkflowException("Workflow type not found"));

        validateCreateWorkflowArguments(workflowTemplate.getArguments(), properties, projectId);

        WorkflowInstance workflowInstance = new WorkflowInstance();
        workflowInstance.setProjectId(projectId);
        workflowInstance.setType(workflowType);
        workflowInstance = workflowInstanceRepository.save(workflowInstance);

        String workflowId = workflowInstance.getId();
        Map<String, Tool> tools = new HashMap<>();

        // Iterate over the tasks in the schema and create new tools for the instance
        for (TaskTemplate taskTemplate : workflowTemplate.getTasks()) {

            String toolName = taskTemplate.getTool();
            if (tools.containsKey(toolName))
                continue;

            ToolTemplate toolTemplate = toolTemplateRepository
                    .findByName(toolName)
                    .orElseThrow(() -> new TemplateNotFound("Tool template not found"));

            AccessTypeTemplate accessType = toolTemplate.getAccess().getType();

            ToolTemplateData toolTemplateData = ToolTemplateData.builder()
                    .projectId(projectId)
                    .workflowId(workflowId)
                    .putAll(properties)
                    .build();

            Tool tool;
            if (accessType == AccessTypeTemplate.LIBRARY)
                tool = toolTemplate.buildLibraryTool(toolTemplateData);
            else
                tool = toolTemplate.buildApiTool(toolTemplateData);

            tools.put(toolName, tool);
        }

        WorkflowTemplateData workflowTemplateData = WorkflowTemplateData.builder()
                .workflowId(workflowId)
                .putAll(properties)
                .build();

        Workflow workflow = workflowTemplate.buildWorkflow(workflowTemplateData);

        workflowInstance.setWorkflow(workflow);

        workflowInstanceRepository.save(workflowInstance);

        for (Tool tool : tools.values())
            flowVizClient.toolService().postTool(tool);

        flowVizClient.workflowService().postWorkflow(workflow);

        return new CreateWorkflowOutput(workflowId);
    }

    private void validateCreateWorkflowArguments(WorkflowTemplateArguments createWorkflowArguments,
                                                 Map<String, String> properties, String projectId) {
        Map<String, String> specialArguments = new HashMap<>();

        properties.keySet().stream().filter(key -> !createWorkflowArguments.containsArgument(key)).findAny()
                .ifPresent(key -> {
                    throw new InvalidWorkflowException("Unknown '" + key + "' argument.");
                });

        createWorkflowArguments.forEach((argumentName, argumentProperties) -> {
            if (!properties.containsKey(argumentName))
                throw new InvalidWorkflowException(String.format("Missing '%s' argument.", argumentName));

            switch (argumentProperties.getType()) {
                case OBJECTID -> validateObjectIdArgument(properties.get(argumentName), argumentName);
                case UUID -> validateUUIDArgument(properties.get(argumentName), argumentName);
                case STRING -> validateStringArgument(properties.get(argumentName), argumentName,
                        argumentProperties.getAllowedValues());
                case DATASETID -> validateDatasetIdArgument(properties, projectId, specialArguments, argumentName,
                        argumentProperties);
                case DISTANCEMATRIXID -> validateDistanceMatrixIdArgument(properties, projectId, specialArguments,
                        argumentName);
                case TREEID -> validateTreeIdArgument(properties, projectId, specialArguments, argumentName);
                case TREEVIEWID -> validateTreeViewIdArgument(properties, projectId, specialArguments, argumentName);
            }
        });
    }

    private void validateObjectIdArgument(String argument, String argumentName) {
        if (!ObjectId.isValid(argument))
            throw new InvalidWorkflowException(String.format("Invalid '%s' argument. Must be a valid ObjectId.", argumentName));
    }

    private void validateUUIDArgument(String argument, String argumentName) {
        if (!UUIDUtils.isValidUUID(argument))
            throw new InvalidWorkflowException(String.format("Invalid '%s' argument. Must be a valid UUID.", argumentName));
    }

    private void validateStringArgument(String argument, String argumentName, List<String> allowedValues) {
        if (!allowedValues.contains(argument))
            throw new InvalidWorkflowException(String.format(
                    "Invalid '%s' argument. Must be one of the allowed values: %s", argumentName, allowedValues)
            );
    }

    private void validateDatasetIdArgument(Map<String, String> properties, String projectId,
                                           Map<String, String> specialArguments, String argumentName,
                                           WorkflowTemplateArgumentProperties argumentProperties) {
        String datasetId = properties.get(argumentName);
        validateObjectIdArgument(datasetId, argumentName);

        specialArguments.put("datasetId", datasetId);

        Dataset dataset = datasetRepository.findByProjectIdAndId(projectId, datasetId)
                .orElseThrow(DatasetDoesNotExistException::new);

        if (argumentProperties.getObtainExtra() == null)
            return;

        argumentProperties.getObtainExtra().forEach((extraArgumentName, extraArgumentProperties) -> {
            switch (extraArgumentProperties.getType()) {
                case TYPINGDATAID -> properties.put(extraArgumentName, dataset.getTypingDataId());
                case ISOLATEDATAID -> properties.put(extraArgumentName, dataset.getIsolateDataId());
            }
        });
    }

    private void validateDistanceMatrixIdArgument(Map<String, String> properties, String projectId,
                                                  Map<String, String> specialArguments, String argumentName) {
        String distanceMatrixId = properties.get(argumentName);
        validateUUIDArgument(distanceMatrixId, argumentName);

        if (!specialArguments.containsKey("datasetId"))
            throw new IllegalStateException("Missing datasetId argument before distanceMatrixId argument.");

        String datasetId = specialArguments.get("datasetId");

        if (!distanceMatrixMetadataRepository.existsByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId))
            throw new DistanceMatrixDoesNotExistException();

        specialArguments.put("distanceMatrixId", distanceMatrixId);
    }

    private void validateTreeIdArgument(Map<String, String> properties, String projectId,
                                        Map<String, String> specialArguments, String argumentName) {
        String treeId = properties.get(argumentName);
        validateUUIDArgument(treeId, argumentName);

        if (!specialArguments.containsKey("datasetId"))
            throw new IllegalStateException("Missing datasetId argument before treeId argument)");

        String datasetId = specialArguments.get("datasetId");

        if (!treeMetadataRepository.existsByProjectIdAndDatasetIdAndTreeId(projectId, datasetId, treeId))
            throw new TreeDoesNotExistException();

        specialArguments.put("treeId", treeId);
    }

    private void validateTreeViewIdArgument(Map<String, String> properties, String projectId,
                                            Map<String, String> specialArguments, String argumentName) {
        String treeViewId = properties.get(argumentName);
        validateUUIDArgument(treeViewId, argumentName);

        if (!specialArguments.containsKey("datasetId"))
            throw new IllegalStateException("Missing datasetId argument before treeViewId argument)");

        String datasetId = specialArguments.get("datasetId");

        if (!treeViewMetadataRepository.existsByProjectIdAndDatasetIdAndTreeViewId(projectId, datasetId, treeViewId))
            throw new TreeViewDoesNotExistException();

        specialArguments.put("treeViewId", treeViewId);
    }
}
