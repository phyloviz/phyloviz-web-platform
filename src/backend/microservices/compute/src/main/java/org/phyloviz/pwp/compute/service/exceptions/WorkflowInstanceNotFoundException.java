package org.phyloviz.pwp.compute.service.exceptions;

public class WorkflowInstanceNotFoundException extends RuntimeException {
    public WorkflowInstanceNotFoundException() {
        super("Workflow not found");
    }
}
