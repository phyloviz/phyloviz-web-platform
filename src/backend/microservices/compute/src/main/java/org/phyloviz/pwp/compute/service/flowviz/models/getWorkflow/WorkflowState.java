package org.phyloviz.pwp.compute.service.flowviz.models.getWorkflow;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum WorkflowState {
    QUEUED("queued"),
    SUCCESS("success"),
    FAILED("failed");

    private final String value;
}
