package org.phyloviz.pwp.compute.service.exceptions;

public class WorkflowInstanceNotFoundException extends RuntimeException {
    public WorkflowInstanceNotFoundException() {
        super("Could not find workflow instance");
    }
}
