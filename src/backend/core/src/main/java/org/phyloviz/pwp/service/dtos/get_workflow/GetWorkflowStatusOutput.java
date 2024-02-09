package org.phyloviz.pwp.service.dtos.get_workflow;

import lombok.Data;
import org.phyloviz.pwp.repository.metadata.templates.workflow_instances.documents.WorkflowStatus;

import java.util.Map;

@Data
public class GetWorkflowStatusOutput {
    private final String workflowId;
    private final String type;
    private final String name;
    private final WorkflowStatus status;
    private final String failureReason;
    private final double progress;
    private final Map<String, Object> data;
}
