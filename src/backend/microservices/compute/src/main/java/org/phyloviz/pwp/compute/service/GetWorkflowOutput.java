package org.phyloviz.pwp.compute.service;

import lombok.Data;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_instances.documents.WorkflowStatus;

import java.util.Map;

@Data
public class GetWorkflowOutput {
    private final String workflowId;
    private final String type;
    private final String name;
    private final WorkflowStatus status;
    private final String failureReason;
    private final Map<String, String> logs;

    private final double progress;
    private final Map<String, Object> data;
}