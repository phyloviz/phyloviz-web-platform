package org.phyloviz.pwp.compute.http.controllers.models.createWorkflow;

import lombok.Data;
import org.phyloviz.pwp.compute.service.dtos.createWorkflow.CreateWorkflowOutput;

@Data
public class CreateWorkflowOutputModel {
    private String workflowId;

    public CreateWorkflowOutputModel(CreateWorkflowOutput createWorkflowOutput) {
        this.workflowId = createWorkflowOutput.getWorkflowId();
    }
}
