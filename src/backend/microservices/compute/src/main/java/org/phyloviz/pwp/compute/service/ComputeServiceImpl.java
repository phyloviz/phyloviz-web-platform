package org.phyloviz.pwp.compute.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
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
import org.phyloviz.pwp.compute.service.flowviz.models.get_workflow.GetWorkflowResponse;
import org.phyloviz.pwp.compute.service.flowviz.models.get_workflow.WorkflowStatus;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.Tool;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.Workflow;
import org.phyloviz.pwp.compute.utils.UUIDUtils;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.DistanceMatrixNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TreeNotFoundException;
import org.phyloviz.pwp.shared.service.project.ProjectAccessService;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetAccessService;
import org.phyloviz.pwp.shared.service.project.dataset.distance_matrix.DistanceMatrixAccessService;
import org.phyloviz.pwp.shared.service.project.dataset.tree.TreeAccessService;
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

    private static final String COMPUTE_DISTANCE_MATRIX = "compute-distance-matrix";
    private static final String COMPUTE_TREE = "compute-tree";
    private static final String COMPUTE_TREE_VIEW = "compute-tree-view";
    private static final String INDEX_TYPING_DATA = "index-typing-data";
    private static final String INDEX_ISOLATE_DATA = "index-isolate-data";
    private static final String INDEX_TREE = "index-tree";
    private static final List<String> COMPUTE_DISTANCE_MATRIX_FUNCTIONS = List.of("hamming"); // TODO: Maybe create an enum for this?
    private static final List<String> COMPUTE_TREE_ALGORITHMS = List.of(
            "goeburst", "edmonds", "sl", "cl", "upgma", "upgmc", "wpgma", "wpgmc", "saitounei",
            "studierkepler", "unj"
    );
    private static final List<String> COMPUTE_TREE_VIEW_LAYOUTS = List.of("radial");
    private final ProjectAccessService projectAccessService;
    private final DatasetAccessService datasetAccessService;
    private final DistanceMatrixAccessService distanceMatrixAccessService;
    private final TreeAccessService treeAccessService;
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
        projectAccessService.assertExists(projectId, userId);

        return switch (workflowType) {
            case COMPUTE_DISTANCE_MATRIX -> createComputeDistanceMatrixWorkflow(projectId, workflowProperties, userId);
            case COMPUTE_TREE -> createComputeTreeWorkflow(projectId, workflowProperties, userId);
            case COMPUTE_TREE_VIEW -> createComputeTreeViewWorkflow(projectId, workflowProperties, userId);
            case INDEX_TYPING_DATA -> createIndexTypingDataWorkflow(projectId, workflowProperties, userId);
            case INDEX_ISOLATE_DATA -> createIndexIsolateDataWorkflow(projectId, workflowProperties, userId);
            case INDEX_TREE -> createIndexTreeWorkflow(projectId, workflowProperties, userId);
            default -> throw new TemplateNotFound("Workflow Type not found");
        };
    }

    @Override
    public GetWorkflowStatusOutput getWorkflowStatus(String projectId, String workflowId, String userId) {
        projectAccessService.assertExists(projectId, userId);

        WorkflowInstance workflowInstance = workflowInstanceRepository
                .findById(workflowId)
                .orElseThrow(WorkflowInstanceNotFoundException::new);

        GetWorkflowResponse workflow = flowVizClient.workflowService().getWorkflow(workflowInstance.getWorkflow().getName());
        WorkflowStatus workflowStatus = workflow.getAirflow().getRuns().get(0);
        String airflowStatus = workflowStatus.getState();

        // Could be simplified, but helpful, so we know the states.
        String status = switch (airflowStatus) {
            case "success" -> "SUCCESS";
            case "running", "queued" -> "RUNNING";
            case "failed" -> "FAILED";
            default -> "FAILED";
        };

        return new GetWorkflowStatusOutput(
                workflowInstance.getId(),
                workflowInstance.getType(),
                status,
                workflowInstance.getResults()
        );
    }

    @Override
    public List<GetWorkflowStatusOutput> getWorkflows(String projectId, String userId) {
        throw new NotImplementedException("Not implemented yet");
    }

    private CreateWorkflowOutput createComputeDistanceMatrixWorkflow(String projectId, Map<String, String> properties, String userId) {
        if (!properties.containsKey("datasetId") || !properties.containsKey("function") || properties.size() != 2)
            throw new IllegalArgumentException("Invalid properties for compute distance matrix workflow");

        String datasetId = properties.get("datasetId");

        if (!ObjectId.isValid(datasetId))
            throw new IllegalArgumentException("Invalid datasetId");

        if (!COMPUTE_DISTANCE_MATRIX_FUNCTIONS.contains(properties.get("function")))
            throw new IllegalArgumentException("Invalid algorithm");

        try {
            Dataset dataset = datasetAccessService.getDataset(projectId, datasetId, userId);

            String typingDataId = dataset.getTypingDataId();

            Map<String, String> workflowProperties = new HashMap<>(properties);
            workflowProperties.put("projectId", projectId);
            workflowProperties.put("typingDataId", typingDataId);

            return createWorkflow(projectId, COMPUTE_DISTANCE_MATRIX, workflowProperties);
        } catch (DatasetNotFoundException e) {
            throw new DatasetDoesNotExistException();
        }
    }

    private CreateWorkflowOutput createComputeTreeWorkflow(String projectId, Map<String, String> properties, String userId) {
        if (!properties.containsKey("datasetId") || !properties.containsKey("distanceMatrixId") ||
                !properties.containsKey("algorithm") || properties.size() != 3)
            throw new IllegalArgumentException("Invalid properties for compute tree workflow");

        String datasetId = properties.get("datasetId");
        String distanceMatrixId = properties.get("distanceMatrixId");

        if (!ObjectId.isValid(datasetId))
            throw new IllegalArgumentException("Invalid datasetId");

        if (!UUIDUtils.isValidUUID(distanceMatrixId))
            throw new IllegalArgumentException("Invalid distanceMatrixId");

        if (!COMPUTE_TREE_ALGORITHMS.contains(properties.get("algorithm")))
            throw new IllegalArgumentException("Invalid algorithm");

        try {
            distanceMatrixAccessService.assertExists(projectId, datasetId, distanceMatrixId, userId);
        } catch (DatasetNotFoundException e) {
            throw new DatasetDoesNotExistException();
        } catch (DistanceMatrixNotFoundException e) {
            throw new DistanceMatrixDoesNotExistException();
        }

        Map<String, String> workflowProperties = new HashMap<>(properties);
        workflowProperties.put("projectId", projectId);

        return createWorkflow(projectId, COMPUTE_TREE, workflowProperties);
    }

    private CreateWorkflowOutput createComputeTreeViewWorkflow(String projectId, Map<String, String> properties, String userId) {
        if (!properties.containsKey("datasetId") || !properties.containsKey("treeId") ||
                !properties.containsKey("layout") || properties.size() != 3)
            throw new IllegalArgumentException("Invalid properties for compute tree view workflow");

        String datasetId = properties.get("datasetId");
        String treeId = properties.get("treeId");

        if (!ObjectId.isValid(datasetId))
            throw new IllegalArgumentException("Invalid datasetId");

        if (!UUIDUtils.isValidUUID(treeId))
            throw new IllegalArgumentException("Invalid treeId");

        if (!COMPUTE_TREE_VIEW_LAYOUTS.contains(properties.get("layout")))
            throw new IllegalArgumentException("Invalid layout");

        try {
            treeAccessService.assertExists(projectId, datasetId, treeId, userId);
        } catch (DatasetNotFoundException e) {
            throw new DatasetDoesNotExistException();
        } catch (TreeNotFoundException e) {
            throw new TreeDoesNotExistException();
        }

        Map<String, String> workflowProperties = new HashMap<>(properties);
        workflowProperties.put("projectId", projectId);

        return createWorkflow(projectId, COMPUTE_TREE_VIEW, workflowProperties);
    }

    private CreateWorkflowOutput createIndexTypingDataWorkflow(String projectId, Map<String, String> properties, String userId) {
        if (!properties.containsKey("datasetId") || properties.size() != 1)
            throw new IllegalArgumentException("Invalid properties for index typing data workflow");

        String datasetId = properties.get("datasetId");

        if (!ObjectId.isValid(datasetId))
            throw new IllegalArgumentException("Invalid datasetId");

        try {
            Dataset dataset = datasetAccessService.getDataset(projectId, datasetId, userId);

            String typingDataId = dataset.getTypingDataId();

            Map<String, String> workflowProperties = new HashMap<>();
            workflowProperties.put("projectId", projectId);
            workflowProperties.put("datasetId", datasetId);
            workflowProperties.put("typingDataId", typingDataId);

            return createWorkflow(projectId, INDEX_TYPING_DATA, workflowProperties);
        } catch (DatasetNotFoundException e) {
            throw new DatasetDoesNotExistException();
        }
    }

    private CreateWorkflowOutput createIndexIsolateDataWorkflow(String projectId, Map<String, String> properties, String userId) {
        if (!properties.containsKey("datasetId") || properties.size() != 1)
            throw new IllegalArgumentException("Invalid properties for index isolate data workflow");

        String datasetId = properties.get("datasetId");

        if (!ObjectId.isValid(datasetId))
            throw new IllegalArgumentException("Invalid datasetId");

        try {
            Dataset dataset = datasetAccessService.getDataset(projectId, datasetId, userId);

            String isolateDataId = dataset.getIsolateDataId();

            Map<String, String> workflowProperties = new HashMap<>();
            workflowProperties.put("projectId", projectId);
            workflowProperties.put("isolateDataId", isolateDataId);

            return createWorkflow(projectId, INDEX_ISOLATE_DATA, workflowProperties);
        } catch (DatasetNotFoundException e) {
            throw new DatasetDoesNotExistException();
        }
    }

    private CreateWorkflowOutput createIndexTreeWorkflow(String projectId, Map<String, String> properties, String userId) {
        if (!properties.containsKey("datasetId") || !properties.containsKey("treeId") || properties.size() != 2)
            throw new IllegalArgumentException("Invalid properties for index tree workflow");

        String datasetId = properties.get("datasetId");
        String treeId = properties.get("treeId");

        if (!ObjectId.isValid(datasetId))
            throw new IllegalArgumentException("Invalid datasetId");

        if (!UUIDUtils.isValidUUID(treeId))
            throw new IllegalArgumentException("Invalid treeId");

        try {
            treeAccessService.assertExists(projectId, datasetId, treeId, userId);
        } catch (DatasetNotFoundException e) {
            throw new DatasetDoesNotExistException();
        } catch (TreeNotFoundException e) {
            throw new TreeDoesNotExistException();
        }

        Map<String, String> workflowProperties = new HashMap<>(properties);
        workflowProperties.put("projectId", projectId);

        return createWorkflow(projectId, INDEX_TREE, workflowProperties);
    }


    private CreateWorkflowOutput createWorkflow(String projectId, String workflowType, Map<String, String> properties) {
        //TODO: Fix transactions

        // Maybe we should only retrieve the workflow template on startup?
        WorkflowTemplate workflowTemplate = workflowTemplateRepository
                .findByName(workflowType)
                .orElseThrow(() -> new TemplateNotFound("Workflow Template not found"));
        // TODO: Add more specific exception to send Workflow Type not found.

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
}
