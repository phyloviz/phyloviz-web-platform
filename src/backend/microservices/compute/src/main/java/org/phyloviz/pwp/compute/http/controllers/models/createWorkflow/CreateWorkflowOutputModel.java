package org.phyloviz.pwp.compute.http.controllers.models.createWorkflow;

import lombok.Data;
import org.phyloviz.pwp.compute.service.dtos.createWorkflow.CreateWorkflowOutputDTO;

@Data
public class CreateWorkflowOutputModel {
    private String workflowId;

    public CreateWorkflowOutputModel(CreateWorkflowOutputDTO createWorkflowOutputDTO) {
        this.workflowId = createWorkflowOutputDTO.getWorkflowId();
    }
}
