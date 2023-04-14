package org.phyloviz.pwp.compute.service;

import org.phyloviz.pwp.compute.service.dtos.createWorkflow.CreateWorkflowOutputDTO;
import org.phyloviz.pwp.compute.service.dtos.getWorkflow.GetWorkflowStatusOutputDTO;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;
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
     * @param projectId    the project id where the workflow will be created
     * @param typingDataId the typing data id
     * @param properties   the properties of the workflow
     * @param userDTO      the userDTO of the user who created the workflow
     * @return the output information of the workflow
     */
    CreateWorkflowOutputDTO createWorkflow(
            String projectId,
            String typingDataId,
            Map<String, String> properties,
            UserDTO userDTO
    );

    /**
     * Gets the status of a workflow.
     *
     * @param projectId  the project id where the workflow is located
     * @param workflowId the workflow id
     * @param userDTO    the userDTO of the user who is requesting the workflow status
     * @return the output information of the workflow
     */
    GetWorkflowStatusOutputDTO getWorkflowStatus(String projectId, String workflowId, UserDTO userDTO);

    /**
     * Gets the workflows of a project.
     *
     * @param projectId the project id where the workflows are located
     * @param userDTO   the userDTO of the user who is requesting the workflows
     * @return the output information of the workflows
     */
    List<GetWorkflowStatusOutputDTO> getWorkflows(String projectId, UserDTO userDTO);
}
