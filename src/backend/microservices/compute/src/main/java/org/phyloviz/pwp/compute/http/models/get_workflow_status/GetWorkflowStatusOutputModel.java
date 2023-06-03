package org.phyloviz.pwp.compute.http.models.get_workflow_status;

import lombok.Data;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_instances.documents.WorkflowStatus;
import org.phyloviz.pwp.compute.service.dtos.get_workflow.GetWorkflowStatusOutput;

import java.util.Map;

@Data
public class GetWorkflowStatusOutputModel {
    private String workflowId;
    private String type;
    private String name;
    private WorkflowStatus status;
    private String failureReason;
    private String failureLog;
    private double progress;
    private Map<String, Object> data;

    public GetWorkflowStatusOutputModel(GetWorkflowStatusOutput getWorkflowStatusOutput) {
        this.workflowId = getWorkflowStatusOutput.getWorkflowId();
        this.type = getWorkflowStatusOutput.getType();
        this.name = getWorkflowStatusOutput.getName();
        this.status = getWorkflowStatusOutput.getStatus();
        this.failureReason = getWorkflowStatusOutput.getFailureReason();
        this.failureLog = getWorkflowStatusOutput.getFailureLog();
        this.progress = getWorkflowStatusOutput.getProgress();
        this.data = getWorkflowStatusOutput.getData();
    }
}
