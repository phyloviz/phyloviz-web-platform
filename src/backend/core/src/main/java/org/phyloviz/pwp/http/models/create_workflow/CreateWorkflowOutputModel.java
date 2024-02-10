package org.phyloviz.pwp.http.models.create_workflow;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.create_workflow.CreateWorkflowOutput;

@Data
public class CreateWorkflowOutputModel {
    private String workflowId;

    public CreateWorkflowOutputModel(CreateWorkflowOutput createWorkflowOutput) {
        this.workflowId = createWorkflowOutput.getWorkflowId();
    }
}
