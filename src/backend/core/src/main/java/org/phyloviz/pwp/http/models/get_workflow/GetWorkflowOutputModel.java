package org.phyloviz.pwp.http.models.get_workflow;

import lombok.Data;
import org.phyloviz.pwp.repository.metadata.templates.workflow_instances.documents.WorkflowStatus;
import org.phyloviz.pwp.service.dtos.get_workflow.GetWorkflowOutput;

import java.util.Map;

@Data
public class GetWorkflowOutputModel {
    private String workflowId;
    private String type;
    private String name;
    private WorkflowStatus status;
    private String failureReason;
    private Map<String, String> logs;
    private double progress;
    private Map<String, Object> data;

    public GetWorkflowOutputModel(GetWorkflowOutput getWorkflowOutput) {
        this.workflowId = getWorkflowOutput.getWorkflowId();
        this.type = getWorkflowOutput.getType();
        this.name = getWorkflowOutput.getName();
        this.status = getWorkflowOutput.getStatus();
        this.failureReason = getWorkflowOutput.getFailureReason();
        this.logs = getWorkflowOutput.getLogs();
        this.progress = getWorkflowOutput.getProgress();
        this.data = getWorkflowOutput.getData();
    }
}
