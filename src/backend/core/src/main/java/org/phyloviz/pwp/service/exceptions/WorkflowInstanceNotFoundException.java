package org.phyloviz.pwp.service.exceptions;

public class WorkflowInstanceNotFoundException extends RuntimeException {
    public WorkflowInstanceNotFoundException() {
        super("Workflow not found");
    }
}
