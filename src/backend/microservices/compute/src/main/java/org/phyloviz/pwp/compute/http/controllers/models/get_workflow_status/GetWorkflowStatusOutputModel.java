package org.phyloviz.pwp.compute.http.controllers.models.get_workflow_status;

import lombok.Data;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_instances.documents.WorkflowStatus;
import org.phyloviz.pwp.compute.service.dtos.get_workflow.GetWorkflowStatusOutput;

import java.util.Map;

@Data
public class GetWorkflowStatusOutputModel {
    private String workflowId;
    private String type;
    private WorkflowStatus status;
    private Map<String, Object> data;

    public GetWorkflowStatusOutputModel(GetWorkflowStatusOutput getWorkflowStatusOutput) {
        this.workflowId = getWorkflowStatusOutput.getWorkflowId();
        this.type = getWorkflowStatusOutput.getType();
        this.status = getWorkflowStatusOutput.getStatus();
        this.data = getWorkflowStatusOutput.getData();
    }
}
