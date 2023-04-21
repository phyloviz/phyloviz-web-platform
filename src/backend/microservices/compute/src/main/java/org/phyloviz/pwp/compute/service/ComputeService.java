package org.phyloviz.pwp.compute.service;

import org.phyloviz.pwp.compute.service.dtos.create_workflow.CreateWorkflowOutput;
import org.phyloviz.pwp.compute.service.dtos.get_workflow.GetWorkflowStatusOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service for the Compute Microservice.
 */
@Service
public interface ComputeService {

    /**
     * Creates a new workflow.
     *
     * @param projectId  the project id where the workflow will be created
     * @param type       the type of the workflow
     * @param properties the properties of the workflow
     * @param userId     the id of the user who created the workflow
     * @return the output information of the workflow
     */
    CreateWorkflowOutput createWorkflow(
            String projectId,
            String type,
            Map<String, String> properties,
            String userId
    );

    /**
     * Gets the status of a workflow.
     *
     * @param projectId  the project id where the workflow is located
     * @param workflowId the workflow id
     * @param userId     the id of the user who is requesting the workflow status
     * @return the output information of the workflow
     */
    GetWorkflowStatusOutput getWorkflowStatus(String projectId, String workflowId, String userId);

    /**
     * Gets the workflows of a project.
     *
     * @param projectId the project id where the workflows are located
     * @param userId    the id of the user who is requesting the workflows
     * @return the output information of the workflows
     */
    List<GetWorkflowStatusOutput> getWorkflows(String projectId, String userId);
}
