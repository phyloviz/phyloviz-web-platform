package org.phyloviz.pwp.compute.service.dtos.getWorkflow;

import lombok.Data;

import java.util.Map;

@Data
public class GetWorkflowStatusOutput {
    private final String workflowId;
    private final String type;
    private final String status;
    private final Map<String, Object> data;
}
