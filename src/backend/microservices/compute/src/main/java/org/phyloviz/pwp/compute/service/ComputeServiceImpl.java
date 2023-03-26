package org.phyloviz.pwp.compute.service;

import java.util.HashMap;
import java.util.Map;
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
import org.phyloviz.pwp.compute.service.dtos.computeDistanceMatrix.ComputeDistanceMatrixOutputDTO;
import org.phyloviz.pwp.compute.service.exceptions.TemplateNotFound;
import org.phyloviz.pwp.compute.service.flowviz.FlowVizClient;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.Tool;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.Workflow;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Implementation of the {@link ComputeService} interface.
 */
@Service
public class ComputeServiceImpl implements ComputeService {

    private final WorkflowTemplateRepository workflowTemplateRepository;
    private final WorkflowInstanceRepository workflowInstanceRepository;
    private final ToolTemplateRepository toolTemplateRepository;
    private final FlowVizClient flowVizClient;
    private final TransactionTemplate transactionTemplate;

    public ComputeServiceImpl(
            WorkflowTemplateRepository workflowTemplateRepository,
            WorkflowInstanceRepository workflowInstanceRepository,
            ToolTemplateRepository toolTemplateRepository,
            FlowVizClient flowVizClient,
            MongoTransactionManager transactionManager
    ) {
        this.workflowTemplateRepository = workflowTemplateRepository;
        this.workflowInstanceRepository = workflowInstanceRepository;
        this.toolTemplateRepository = toolTemplateRepository;
        this.flowVizClient = flowVizClient;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }


    private final String computeDistanceMatrixWorkflowTemplateName = "computeDistanceMatrix";

    @Override
    public ComputeDistanceMatrixOutputDTO computeDistanceMatrix(String projectId, String typingDatasetId, UserDTO toDTO) {
        // Maybe we should only retrieve the workflow template on startup?

        //TODO: Fix transactions
        WorkflowTemplate workflowTemplate = workflowTemplateRepository.findByName(computeDistanceMatrixWorkflowTemplateName)
                .orElseThrow(() -> new TemplateNotFound("Workflow template not found"));

        WorkflowInstance workflowInstance = new WorkflowInstance();
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
                .put("typingDatasetId", typingDatasetId)
                .build();

        Workflow workflow = workflowTemplate.buildWorkflow(workflowTemplateData);

        workflowInstance.setWorkflow(workflow);

        workflowInstanceRepository.save(workflowInstance);

        for (Tool tool : tools.values())
            flowVizClient.toolService().postTool(tool);

        flowVizClient.workflowService().postWorkflow(workflow);

        return new ComputeDistanceMatrixOutputDTO(workflowId);
    }

}
