package org.phyloviz.pwp.compute.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.ToolTemplateRepository;
import org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.ToolTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.ToolTemplateData;
import org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.access.AccessTypeTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_instances.WorkflowInstanceRepository;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_instances.documents.WorkflowInstance;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.WorkflowTemplateRepository;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents.TaskTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents.WorkflowTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents.WorkflowTemplateData;
import org.phyloviz.pwp.compute.service.dtos.create_workflow.CreateWorkflowOutput;
import org.phyloviz.pwp.compute.service.dtos.get_workflow.GetWorkflowStatusOutput;
import org.phyloviz.pwp.compute.service.exceptions.DatasetDoesNotExistException;
import org.phyloviz.pwp.compute.service.exceptions.DistanceMatrixDoesNotExistException;
import org.phyloviz.pwp.compute.service.exceptions.TemplateNotFound;
import org.phyloviz.pwp.compute.service.exceptions.TreeDoesNotExistException;
import org.phyloviz.pwp.compute.service.exceptions.WorkflowInstanceNotFoundException;
import org.phyloviz.pwp.compute.service.flowviz.FLOWViZClient;
import org.phyloviz.pwp.compute.service.flowviz.exceptions.UnexpectedResponseException;
import org.phyloviz.pwp.compute.service.flowviz.models.get_workflow.GetWorkflowResponse;
import org.phyloviz.pwp.compute.service.flowviz.models.get_workflow.WorkflowStatus;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.Tool;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.Workflow;
import org.phyloviz.pwp.compute.utils.UUIDUtils;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
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

    private static final List<String> COMPUTE_DISTANCE_MATRIX_FUNCTIONS = List.of("hamming"); // TODO: Maybe create an enum for this?
    private static final List<String> COMPUTE_TREE_ALGORITHMS = List.of(
            "goeburst", "edmonds", "sl", "cl", "upgma", "upgmc", "wpgma", "wpgmc", "saitounei",
            "studierkepler", "unj"
    );
    private static final List<String> COMPUTE_TREE_VIEW_LAYOUTS = List.of("radial", "force-direct");

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;

    private final DistanceMatrixMetadataRepository distanceMatrixMetadataRepository;
    private final TreeMetadataRepository treeMetadataRepository;

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

        GetWorkflowResponse workflow;
        try {
            workflow = flowVizClient.workflowService().getWorkflow(workflowInstance.getWorkflow().getName());
        } catch (UnexpectedResponseException e) {
            if (e.getResponse().code() == 404) {
                return new GetWorkflowStatusOutput(
                        workflowInstance.getId(),
                        workflowInstance.getType(),
                        "RUNNING",
                        workflowInstance.getData()
                );
            }
            throw e;
        }

        List<WorkflowStatus> workflowRuns = workflow.getAirflow().getRuns();

        if (workflowRuns.isEmpty())
            return new GetWorkflowStatusOutput(
                    workflowInstance.getId(),
                    workflowInstance.getType(),
                    "RUNNING",
                    workflowInstance.getData()
            );

        WorkflowStatus workflowStatus = workflowRuns.get(0);

        String airflowStatus = workflowStatus.getState();

        return new GetWorkflowStatusOutput(
                workflowInstance.getId(),
                workflowInstance.getType(),
                airflowStatus.toUpperCase(),
                workflowInstance.getData()
        );
    }

    @Override
    public List<GetWorkflowStatusOutput> getWorkflows(String projectId, String userId) {
        return List.of(); // TODO Implement
    }

    private CreateWorkflowOutput createWorkflow(String projectId, String workflowType, Map<String, String> properties) {
        //TODO: Fix transactions

        // Maybe we should only retrieve the workflow template on startup?
        WorkflowTemplate workflowTemplate = workflowTemplateRepository
                .findByName(workflowType)
                .orElseThrow(() -> new TemplateNotFound("Workflow Template not found"));
        // TODO: Add more specific exception to send Workflow Type not found.

        validateCreateWorkflowArguments(workflowTemplate.getArguments(), properties, projectId);

        // TODO: Maybe use uuids instead of mongo ids?
        WorkflowInstance workflowInstance = new WorkflowInstance();
        workflowInstance.setType(workflowType);
        workflowInstance = workflowInstanceRepository.save(workflowInstance);

        String workflowId = workflowInstance.getId();
        Map<String, Tool> tools = new HashMap<>();

        // Iterate over the tasks in the schema and create new tasks for the instance
        for (TaskTemplate taskTemplate : workflowTemplate.getTasks()) {

            String toolName = taskTemplate.getTool();
            if (tools.containsKey(toolName))
                continue;

            ToolTemplate toolTemplate = toolTemplateRepository
                    .findByName(taskTemplate.getTool())
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

    private void validateCreateWorkflowArguments(Map<String, Map<String, Object>> createWorkflowArguments,
                                                 Map<String, String> properties, String projectId) {
        if (properties.size() != createWorkflowArguments.size() - (createWorkflowArguments.containsKey("extra") ? 1 : 0))
            throw new IllegalArgumentException("Invalid properties");

        if (createWorkflowArguments.containsKey("extra")) {
            Map<String, Object> extraArguments = createWorkflowArguments.get("extra");
            extraArguments.forEach((argumentName, argumentProperties) -> {
                Map<String, Object> argumentPropertiesMap = (Map<String, Object>) argumentProperties;
                if (argumentPropertiesMap.containsKey("special")) {
                    if (argumentPropertiesMap.get("special").equals("project")) {
                        properties.put(argumentName, projectId);
                    }
                }
            });
        }

        Map<String, String> specialArguments = new HashMap<>();

        for (Map.Entry<String, Map<String, Object>> entry : createWorkflowArguments.entrySet()) {
            String argumentName = entry.getKey();
            Map<String, Object> argumentProperties = entry.getValue();

            if (argumentName.equals("extra"))
                continue;

            if (!properties.containsKey(argumentName))
                throw new IllegalArgumentException("Missing " + argumentName);

            switch ((String) argumentProperties.get("type")) {
                case "objectId" -> {
                    if (!ObjectId.isValid(properties.get(argumentName)))
                        throw new IllegalArgumentException("Invalid " + argumentName);
                }
                case "uuid" -> {
                    if (!UUIDUtils.isValidUUID(properties.get(argumentName)))
                        throw new IllegalArgumentException("Invalid " + argumentName);
                }
                case "string" -> {
                    if (properties.get(argumentName).isEmpty())
                        throw new IllegalArgumentException("Invalid " + argumentName);
                }
            }

            if (argumentProperties.containsKey("special")) {
                Object special = argumentProperties.get("special");
                if (special instanceof Map) {
                    Map<String, Map<String, Object>> specialMap = (Map<String, Map<String, Object>>) special;

                    if (specialMap.containsKey("dataset")) {
                        String datasetId = properties.get(argumentName);
                        specialArguments.put("datasetId", datasetId);

                        if (specialMap.get("dataset").containsKey("obtain-extra")) {
                            List<String> obtainExtra = (List<String>) specialMap.get("dataset").get("obtain-extra");

                            if (obtainExtra.isEmpty() && !datasetRepository.existsByProjectIdAndId(projectId, datasetId)) {
                                throw new DatasetDoesNotExistException();
                            }

                            obtainExtra.forEach(obtainType -> {
                                if (obtainType.equals("typingDataId")) {
                                    Dataset dataset = datasetRepository.findByProjectIdAndId(projectId, datasetId)
                                            .orElseThrow(DatasetDoesNotExistException::new);
                                    properties.put("typingDataId", dataset.getTypingDataId());
                                }
                                if (obtainType.equals("isolateDataId")) {
                                    Dataset dataset = datasetRepository.findByProjectIdAndId(projectId, datasetId)
                                            .orElseThrow(DatasetDoesNotExistException::new);
                                    properties.put("isolateDataId", dataset.getIsolateDataId());
                                }
                            });
                        }
                    }
                } else if (special instanceof String) {
                    switch ((String) special) {
                        case "dataset" -> {
                            String datasetId = properties.get(argumentName);
                            specialArguments.put("datasetId", datasetId);
                            if (!datasetRepository.existsByProjectIdAndId(projectId, datasetId)) {
                                throw new DatasetDoesNotExistException();
                            }
                        }
                        case "distanceMatrix" -> {
                            if (!specialArguments.containsKey("datasetId"))
                                throw new IllegalArgumentException("Missing datasetId argument before distanceMatrixId argument)");

                            String datasetId = specialArguments.get("datasetId");

                            if (!distanceMatrixMetadataRepository.existsByProjectIdAndDatasetIdAndDistanceMatrixId(
                                    projectId, datasetId, argumentName
                            )) {
                                throw new DistanceMatrixDoesNotExistException();
                            }

                            specialArguments.put("distanceMatrixId", properties.get(argumentName));
                        }
                        case "tree" -> {
                            if (!specialArguments.containsKey("datasetId"))
                                throw new IllegalArgumentException("Missing datasetId argument before treeId argument)");

                            String datasetId = specialArguments.get("datasetId");

                            if (!treeMetadataRepository.existsByProjectIdAndDatasetIdAndTreeId(
                                    projectId, datasetId, argumentName
                            )) {
                                throw new TreeDoesNotExistException();
                            }

                            specialArguments.put("treeId", properties.get(argumentName));
                        }
                    }
                }
            }
        }
    }
}
