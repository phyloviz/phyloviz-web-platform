package org.phyloviz.pwp.service.flowviz.models.get_workflow;


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
