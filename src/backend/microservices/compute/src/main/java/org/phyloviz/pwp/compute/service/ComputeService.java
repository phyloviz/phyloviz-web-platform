package org.phyloviz.pwp.compute.service;

import java.util.Map;
import org.phyloviz.pwp.compute.service.dtos.computeDistanceMatrix.CreateWorkflowOutputDTO;
import org.phyloviz.pwp.compute.service.dtos.getWorkflow.GetWorkflowOutputDTO;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;
import org.springframework.stereotype.Service;

/**
 * Service for the Compute Microservice.
 */
@Service
public interface ComputeService {

    CreateWorkflowOutputDTO createWorkflow(String projectId, String typingDataId, Map<String, String> properties, UserDTO userDTO);

    GetWorkflowOutputDTO getWorkflow(String projectId, String workflowId, UserDTO dto);
}
