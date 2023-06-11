package org.phyloviz.pwp.compute.service;

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
import org.phyloviz.pwp.compute.service.dtos.create_workflow.CreateWorkflowOutput;
import org.phyloviz.pwp.compute.service.dtos.get_workflow.GetWorkflowOutput;
import org.phyloviz.pwp.compute.service.dtos.get_workflow.GetWorkflowStatusOutput;
import org.phyloviz.pwp.compute.service.exceptions.InvalidWorkflowException;
import org.phyloviz.pwp.compute.service.exceptions.TemplateNotFound;
import org.phyloviz.pwp.compute.service.exceptions.WorkflowInstanceNotFoundException;
import org.phyloviz.pwp.compute.service.flowviz.FLOWViZClient;
import org.phyloviz.pwp.compute.service.flowviz.exceptions.UnexpectedResponseException;
import org.phyloviz.pwp.compute.service.flowviz.models.get_workflow.AirflowWorkflowStatus;
import org.phyloviz.pwp.compute.service.flowviz.models.get_workflow.GetWorkflowResponse;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.Tool;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.GetWorkflowTaskLogResponse;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.Workflow;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ComputeServiceImpl implements ComputeService {

    private final ProjectRepository projectRepository;

    private final WorkflowTemplateRepository workflowTemplateRepository;
    private final WorkflowInstanceRepository workflowInstanceRepository;
    private final ToolTemplateRepository toolTemplateRepository;
    private final FLOWViZClient flowVizClient;

    /**
     * Constructor. Calls the {@link WorkflowTemplateRepository#findAll()} method to validate the workflow templates
     * using the constructor.
     */
    ComputeServiceImpl(
            ProjectRepository projectRepository,
            WorkflowTemplateRepository workflowTemplateRepository,
            WorkflowInstanceRepository workflowInstanceRepository,
            ToolTemplateRepository toolTemplateRepository,
            FLOWViZClient flowVizClient
    ) {
        this.projectRepository = projectRepository;
        this.workflowTemplateRepository = workflowTemplateRepository;
        this.workflowInstanceRepository = workflowInstanceRepository;
        this.toolTemplateRepository = toolTemplateRepository;
        this.flowVizClient = flowVizClient;

        workflowTemplateRepository.findAll();
    }

    @Override
    public CreateWorkflowOutput createWorkflow(
            String projectId,
            String workflowType,
            Map<String, String> workflowProperties,
            String userId
    ) {
        validateObjectIdArgument(projectId, "projectId");

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
    public GetWorkflowOutput getWorkflow(String projectId, String workflowId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        WorkflowInstance workflowInstance = workflowInstanceRepository
                .findById(workflowId)
                .orElseThrow(WorkflowInstanceNotFoundException::new);

        return getWorkflowOutput(workflowInstance);
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

    /**
     * Gets the status of the workflow.
     * <p>
     * If the workflow instance metadata has the status as running, updates the workflow instance status by calling the FLOWViZ API to get the status of
     * the workflow to confirm if it is still running.
     *
     * @param workflowInstance the workflow instance metadata
     * @return information about the workflow
     */
    private GetWorkflowStatusOutput getWorkflowStatusOutput(WorkflowInstance workflowInstance) {
        if (workflowInstance.getStatus() == WorkflowStatus.RUNNING) {
            updateWorkflowStatusFromFLOWViZ(workflowInstance);
        }

        return new GetWorkflowStatusOutput(
                workflowInstance.getId(),
                workflowInstance.getType(),
                workflowInstance.getName(),
                workflowInstance.getStatus(),
                workflowInstance.getFailureReason(),
                workflowInstance.getProgress(),
                workflowInstance.getData()
        );
    }

    /**
     * Gets the status of the workflow.
     * <p>
     * If the workflow instance metadata has the status as running, updates the workflow instance status by calling the FLOWViZ API to get the status of
     * the workflow to confirm if it is still running.
     *
     * @param workflowInstance the workflow instance metadata
     * @return information about the workflow
     */
    private GetWorkflowOutput getWorkflowOutput(WorkflowInstance workflowInstance) {
        updateWorkflowFromFLOWViZ(workflowInstance);

        return new GetWorkflowOutput(
                workflowInstance.getId(),
                workflowInstance.getType(),
                workflowInstance.getName(),
                workflowInstance.getStatus(),
                workflowInstance.getFailureReason(),
                workflowInstance.getLogs(),
                workflowInstance.getProgress(),
                workflowInstance.getData()
        );
    }

    /**
     * Updates the workflow status using the FLOWViZ API, getting the status of the workflow to confirm if it is still running.
     *
     * @param workflowInstance the workflow instance metadata
     */
    private void updateWorkflowStatusFromFLOWViZ(WorkflowInstance workflowInstance) {
        GetWorkflowResponse workflow;
        try {
            workflow = flowVizClient.workflowService().getWorkflow(workflowInstance.getWorkflow().getName());
        } catch (UnexpectedResponseException e) {
            if (e.getResponse().code() == 404) {
                return;
            } else throw e;
        }

        List<AirflowWorkflowStatus> workflowRuns = workflow.getAirflow().getRuns();

        if (workflowRuns.isEmpty())
            return;

        AirflowWorkflowStatus airflowWorkflowStatus = workflowRuns.get(0);

        String airflowStatus = airflowWorkflowStatus.getState();

        switch (airflowStatus) {
            case "running", "queued", "no_status" -> {
            }
            case "success" -> {
                workflowInstance.setStatus(WorkflowStatus.SUCCESS);
                workflowInstance.setProgress(100);
                workflowInstanceRepository.save(workflowInstance);
            }
            case "failed" -> {
                workflowInstance.setStatus(WorkflowStatus.FAILED);
                if (workflowInstance.getFailureReason() == null) {
                    workflowInstance.setFailureReason("Internal Server Error");
                }
                workflowInstanceRepository.save(workflowInstance);
            }
            default -> throw new IllegalStateException("Unexpected airflow status value: " + airflowStatus);
        }
    }

    /**
     * Updates the workflow using the FLOWViZ API.
     *
     * @param workflowInstance the workflow instance metadata
     */
    private void updateWorkflowFromFLOWViZ(WorkflowInstance workflowInstance) {
        GetWorkflowResponse workflow;
        try {
            workflow = flowVizClient.workflowService().getWorkflow(workflowInstance.getWorkflow().getName());

        } catch (UnexpectedResponseException e) {
            if (e.getResponse().code() == 404) {
                return;
            } else throw e;
        }

        List<AirflowWorkflowStatus> workflowRuns = workflow.getAirflow().getRuns();

        if (workflowRuns.isEmpty())
            return;

        AirflowWorkflowStatus airflowWorkflowStatus = workflowRuns.get(0);

        String airflowStatus = airflowWorkflowStatus.getState();
        String dagRunId = airflowWorkflowStatus.getDagRunId();

        Map<String, String> logs = new HashMap<>(workflowInstance.getWorkflow().getTasks().size());

        workflowInstance.getWorkflow().getTasks().forEach((task) -> {
            try {
                GetWorkflowTaskLogResponse workflowTaskLogResponse;
                workflowTaskLogResponse = flowVizClient.workflowService().getWorkflowDagRunTaskLog(workflowInstance.getWorkflow().getName(), dagRunId, "task_" + task.getId(), "1");

                logs.put(task.getId(), workflowTaskLogResponse.getContent());
            } catch (UnexpectedResponseException e) {
                if (e.getResponse().code() != 404)
                    throw e;
            }
        });

        workflowInstance.setLogs(logs);
        workflowInstanceRepository.save(workflowInstance);

        switch (airflowStatus) {
            case "running", "queued", "no_status" -> {
            }
            case "success" -> {
                workflowInstance.setStatus(WorkflowStatus.SUCCESS);
                workflowInstance.setProgress(100);
                workflowInstanceRepository.save(workflowInstance);
            }
            case "failed" -> {
                workflowInstance.setStatus(WorkflowStatus.FAILED);
                if (workflowInstance.getFailureReason() == null) {
                    workflowInstance.setFailureReason("Internal Server Error");
                }
                workflowInstanceRepository.save(workflowInstance);
            }
            default -> throw new IllegalStateException("Unexpected airflow status value: " + airflowStatus);
        }
    }

    private CreateWorkflowOutput createWorkflow(String projectId, String workflowType, Map<String, String> properties) {
        //TODO: Fix transactions

        WorkflowTemplate workflowTemplate = workflowTemplateRepository
                .findByType(workflowType)
                .orElseThrow(() -> new InvalidWorkflowException("Workflow type not found"));

        validateCreateWorkflowArguments(workflowTemplate.getArguments(), properties);

        Map<String, Object> workflowInstanceData = new HashMap<>(properties);
        workflowInstanceData.put("projectId", projectId);

        WorkflowInstance workflowInstance = new WorkflowInstance();
        workflowInstance.setProjectId(projectId);
        workflowInstance.setType(workflowType);
        workflowInstance.setName(workflowTemplate.getName());
        workflowInstance.setStatus(WorkflowStatus.RUNNING);
        workflowInstance.setProgress(0.0);
        workflowInstance.setData(workflowInstanceData);
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
                .projectId(projectId)
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

    /**
     * Validates that the arguments received in the request are valid for the workflow template. Verifies that all
     * required arguments are present and that the types are correct.
     *
     * @param createWorkflowArguments the arguments defined in the workflow template
     * @param properties              the arguments received in the request
     */
    private void validateCreateWorkflowArguments(Map<String, WorkflowTemplateArgumentProperties> createWorkflowArguments,
                                                 Map<String, String> properties) {
        properties.keySet().stream().filter(key -> !createWorkflowArguments.containsKey(key)).findAny()
                .ifPresent(key -> {
                    throw new InvalidWorkflowException(String.format("Unknown argument: '%s'", key));
                });

        createWorkflowArguments.forEach((argumentName, argumentProperties) -> {
            boolean propertyExists = properties.containsKey(argumentName);
            if (argumentProperties.isRequired() && !propertyExists)
                throw new InvalidWorkflowException(String.format("Missing argument: '%s'", argumentName));

            switch (argumentProperties.getType()) {
                case OBJECTID, DATASETID -> validateObjectIdArgument(properties.get(argumentName), argumentName);
                case UUID, DISTANCEMATRIXID, TREEID, TREEVIEWID ->
                        validateUUIDArgument(properties.get(argumentName), argumentName);
                case STRING -> validateStringArgument(properties.get(argumentName), argumentName,
                        argumentProperties.getAllowedValues());
                case NUMBER -> validateNumberArgument(properties.get(argumentName), argumentName);
            }

            if (argumentProperties.getPrefix() != null && properties.containsKey(argumentName)) {
                properties.put(argumentName, argumentProperties.getPrefix() + properties.get(argumentName));
            }
        });
    }

    private void validateObjectIdArgument(String argument, String argumentName) {
        if (!ObjectId.isValid(argument))
            throw new InvalidWorkflowException(String.format("Invalid argument: '%s'. Must be a valid ObjectId.", argumentName));
    }

    private void validateUUIDArgument(String argument, String argumentName) {
        if (!UUIDUtils.isValidUUID(argument))
            throw new InvalidWorkflowException(String.format("Invalid argument: '%s'. Must be a valid UUID.", argumentName));
    }

    private void validateStringArgument(String argument, String argumentName, List<String> allowedValues) {
        if (!allowedValues.contains(argument))
            throw new InvalidWorkflowException(String.format(
                    "Invalid argument: '%s'. Must be one of the allowed values: %s", argumentName, allowedValues)
            );
    }

    private void validateNumberArgument(String argument, String argumentName) {
        try {
            Double.parseDouble(argument);
        } catch (NumberFormatException e) {
            throw new InvalidWorkflowException(String.format("Invalid argument: '%s'. Must be a number.", argumentName));
        }
    }
}
