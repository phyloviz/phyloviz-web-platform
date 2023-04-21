package org.phyloviz.pwp.compute.http.controllers.models.create_workflow;

import lombok.Data;
import org.phyloviz.pwp.compute.service.dtos.create_workflow.CreateWorkflowOutput;

@Data
public class CreateWorkflowOutputModel {
    private String workflowId;

    public CreateWorkflowOutputModel(CreateWorkflowOutput createWorkflowOutput) {
        this.workflowId = createWorkflowOutput.getWorkflowId();
    }
}
