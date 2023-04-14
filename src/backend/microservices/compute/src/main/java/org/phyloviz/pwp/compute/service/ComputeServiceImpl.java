package org.phyloviz.pwp.compute.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.ToolTemplateRepository;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.ToolTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.ToolTemplateData;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.access.AccessTypeTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflowInstances.WorkflowInstanceRepository;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflowInstances.documents.WorkflowInstance;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflowTemplate.WorkflowTemplateRepository;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflowTemplate.documents.TaskTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflowTemplate.documents.WorkflowTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflowTemplate.documents.WorkflowTemplateData;
import org.phyloviz.pwp.compute.service.dtos.computeDistanceMatrix.CreateWorkflowOutputDTO;
import org.phyloviz.pwp.compute.service.dtos.getWorkflow.GetWorkflowOutputDTO;
import org.phyloviz.pwp.compute.service.exceptions.TemplateNotFound;
import org.phyloviz.pwp.compute.service.exceptions.WorkflowInstanceNotFoundException;
import org.phyloviz.pwp.compute.service.flowviz.FlowVizClient;
import org.phyloviz.pwp.compute.service.flowviz.models.getWorkflow.GetWorkflowResponse;
import org.phyloviz.pwp.compute.service.flowviz.models.getWorkflow.WorkflowStatus;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.Tool;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.Workflow;
import org.phyloviz.pwp.compute.utils.UUIDUtils;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.service.ProjectService;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
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

    private final WorkflowTemplateRepository workflowTemplateRepository;
    private final WorkflowInstanceRepository workflowInstanceRepository;
    private final DatasetRepository datasetRepository;
    private final ToolTemplateRepository toolTemplateRepository;
    private final TreeMetadataRepository treeMetadataRepository;
    private final ProjectService projectService;
    private final FlowVizClient flowVizClient;

    private static final String COMPUTE_DISTANCE_MATRIX = "compute-distance-matrix";
    private static final String COMPUTE_TREE = "compute-tree";
    private static final String COMPUTE_TREE_VIEW = "compute-tree-view";
    private static final String INDEX_TYPING_DATA = "index-typing-data";
    private static final String INDEX_ISOLATE_DATA = "index-isolate-data";
    private static final String INDEX_TREE = "index-tree";
    private static final List<String> COMPUTE_DISTANCE_MATRIX_FUNCTIONS = List.of("hamming");
    private static final List<String> COMPUTE_TREE_ALGORITHMS = List.of("goeburst", "edmonds", "sl", "cl", "upgma", "upgmc", "wpgma", "wpgmc", "saitounei", "studierkepler", "unj");
    private static final List<String> COMPUTE_TREE_VIEW_LAYOUTS = List.of("radial");

    @Override
    public CreateWorkflowOutputDTO createWorkflow(String projectId, String workflowType, Map<String, String> workflowProperties, UserDTO userDTO) {
        projectService.assertHasAccess(projectId, userDTO.getId());

        return switch (workflowType) {
            case COMPUTE_DISTANCE_MATRIX -> createComputeDistanceMatrixWorkflow(projectId, workflowProperties);
            case COMPUTE_TREE -> createComputeTreeWorkflow(projectId, workflowProperties);
            case COMPUTE_TREE_VIEW -> createComputeTreeViewWorkflow(projectId, workflowProperties);
            case INDEX_TYPING_DATA -> createIndexTypingDataWorkflow(projectId, workflowProperties);
            case INDEX_ISOLATE_DATA -> createIndexIsolateDataWorkflow(projectId, workflowProperties);
            case INDEX_TREE -> createIndexTreeWorkflow(projectId, workflowProperties);
            default -> throw new TemplateNotFound("Workflow Type not found");
        };
    }

    @Override
    public GetWorkflowOutputDTO getWorkflow(String projectId, String workflowId, UserDTO userDTO) {
        projectService.assertHasAccess(projectId, userDTO.getId());

        WorkflowInstance workflowInstance = workflowInstanceRepository.findById(workflowId).orElseThrow(WorkflowInstanceNotFoundException::new);

        GetWorkflowResponse workflow = flowVizClient.workflowService().getWorkflow(workflowInstance.getWorkflow().getName());
        WorkflowStatus workflowStatus = workflow.getAirflow().getRuns().get(0);
        String airflowStatus = workflowStatus.getState();

        // Could be simplified, but helpful so we know the states.
        String status = switch (airflowStatus) {
            case "success" -> "SUCCESS";
            case "running", "queued" -> "RUNNING";
            case "failed" -> "FAILED";
            default -> "FAILED";
        };

        return new GetWorkflowOutputDTO(workflowInstance.getId(), workflowInstance.getType(), status, workflowInstance.getResults());
    }


    private CreateWorkflowOutputDTO createComputeDistanceMatrixWorkflow(String projectId, Map<String, String> properties) {
        if (!properties.containsKey("datasetId") || !properties.containsKey("function") || properties.size() != 2)
            throw new IllegalArgumentException("Invalid properties for compute distance matrix workflow");

        if (!ObjectId.isValid(properties.get("datasetId")))
            throw new IllegalArgumentException("Invalid datasetId");

        if (!COMPUTE_DISTANCE_MATRIX_FUNCTIONS.contains(properties.get("function")))
            throw new IllegalArgumentException("Invalid algorithm");

        Map<String, String> workflowProperties = new HashMap<>(properties);

        String datasetId = properties.get("datasetId");

        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(DatasetNotFoundException::new);

        String typingDataId = dataset.getTypingDataId();

        workflowProperties.put("typingDataId", typingDataId);
        workflowProperties.put("projectId", projectId);

        return createWorkflow(projectId, COMPUTE_DISTANCE_MATRIX, workflowProperties);
    }

    private CreateWorkflowOutputDTO createComputeTreeWorkflow(String projectId, Map<String, String> properties) {
        if (!properties.containsKey("datasetId") || !properties.containsKey("distanceMatrixId") || !properties.containsKey("algorithm") || properties.size() != 3)
            throw new IllegalArgumentException("Invalid properties for compute tree workflow");

        if (!ObjectId.isValid(properties.get("datasetId")))
            throw new IllegalArgumentException("Invalid datasetId");

        if (!UUIDUtils.isValidUUID(properties.get("distanceMatrixId")))
            throw new IllegalArgumentException("Invalid distanceMatrixId");

        if (!COMPUTE_TREE_ALGORITHMS.contains(properties.get("algorithm")))
            throw new IllegalArgumentException("Invalid algorithm");

        Map<String, String> workflowProperties = new HashMap<>(properties);
        workflowProperties.put("projectId", projectId);

        return createWorkflow(projectId, COMPUTE_TREE, workflowProperties);
    }

    private CreateWorkflowOutputDTO createComputeTreeViewWorkflow(String projectId, Map<String, String> properties) {
        if (!properties.containsKey("datasetId") || !properties.containsKey("treeId") || !properties.containsKey("layout") || properties.size() != 3)
            throw new IllegalArgumentException("Invalid properties for compute tree view workflow");

        if (!UUIDUtils.isValidUUID(properties.get("treeId")))
            throw new IllegalArgumentException("Invalid treeId");

        if (!ObjectId.isValid(properties.get("datasetId")))
            throw new IllegalArgumentException("Invalid datasetId");

        if (!COMPUTE_TREE_VIEW_LAYOUTS.contains(properties.get("layout")))
            throw new IllegalArgumentException("Invalid layout");


        Map<String, String> workflowProperties = new HashMap<>(properties);
        workflowProperties.put("projectId", projectId);

        return createWorkflow(projectId, COMPUTE_TREE_VIEW, workflowProperties);
    }

    private CreateWorkflowOutputDTO createIndexTypingDataWorkflow(String projectId, Map<String, String> properties) {
        if (!properties.containsKey("datasetId") || properties.size() != 1)
            throw new IllegalArgumentException("Invalid properties for index typing data workflow");

        if (!ObjectId.isValid(properties.get("datasetId")))
            throw new IllegalArgumentException("Invalid datasetId");

        Map<String, String> workflowProperties = new HashMap<>();

        String datasetId = properties.get("datasetId");

        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(DatasetNotFoundException::new);

        String typingDataId = dataset.getTypingDataId();

        workflowProperties.put("datasetId", datasetId);
        workflowProperties.put("typingDataId", typingDataId);
        workflowProperties.put("projectId", projectId);

        return createWorkflow(projectId, INDEX_TYPING_DATA, workflowProperties);
    }

    private CreateWorkflowOutputDTO createIndexIsolateDataWorkflow(String projectId, Map<String, String> properties) {
        if (!properties.containsKey("datasetId") || properties.size() != 1)
            throw new IllegalArgumentException("Invalid properties for index isolate data workflow");

        if (!ObjectId.isValid(properties.get("datasetId")))
            throw new IllegalArgumentException("Invalid datasetId");

        Map<String, String> workflowProperties = new HashMap<>();

        String datasetId = properties.get("datasetId");

        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(DatasetNotFoundException::new);

        String isolateDataId = dataset.getIsolateDataId();

        workflowProperties.put("isolateDataId", isolateDataId);
        workflowProperties.put("projectId", projectId);

        return createWorkflow(projectId, INDEX_ISOLATE_DATA, workflowProperties);
    }

    private CreateWorkflowOutputDTO createIndexTreeWorkflow(String projectId, Map<String, String> properties) {
        if (!properties.containsKey("datasetId") || !properties.containsKey("treeId") || properties.size() != 2)
            throw new IllegalArgumentException("Invalid properties for index tree workflow");

        if (!ObjectId.isValid(properties.get("datasetId")))
            throw new IllegalArgumentException("Invalid datasetId");

        if (!UUIDUtils.isValidUUID(properties.get("treeId")))
            throw new IllegalArgumentException("Invalid treeId");

        Map<String, String> workflowProperties = new HashMap<>(properties);

        workflowProperties.put("projectId", projectId);

        return createWorkflow(projectId, INDEX_TREE, workflowProperties);
    }


    private CreateWorkflowOutputDTO createWorkflow(String projectId, String workflowType, Map<String, String> properties) {
        //TODO: Fix transactions

        // Maybe we should only retrieve the workflow template on startup?
        WorkflowTemplate workflowTemplate = workflowTemplateRepository.findByName(workflowType)
                .orElseThrow(() -> new TemplateNotFound("Workflow Template not found")); // TODO: Add more specific exception to send Workflow Type not found.

        //TODO: Maybe use uuids instead of mongo ids?
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

            ToolTemplate toolTemplate = toolTemplateRepository.findByName(taskTemplate.getTool())
                    .orElseThrow(() -> new TemplateNotFound("Tool template not found"));

            AccessTypeTemplate accessType = toolTemplate.getAccess().getType();

            ToolTemplateData toolTemplateData = ToolTemplateData.builder()
                    .projectId(projectId)
                    .workflowId(workflowId)
                    .putAll(properties)
                    .build();

            Tool tool;
            if (accessType == AccessTypeTemplate.LIBRARY) {
                tool = toolTemplate.buildLibraryTool(toolTemplateData);
            } else {
                tool = toolTemplate.buildApiTool(toolTemplateData);
            }

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

        return new CreateWorkflowOutputDTO(workflowId);
    }

}
