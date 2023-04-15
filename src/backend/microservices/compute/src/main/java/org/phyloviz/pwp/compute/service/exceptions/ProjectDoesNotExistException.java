package org.phyloviz.pwp.compute.service.exceptions;

public class ProjectDoesNotExistException extends RuntimeException {
    public ProjectDoesNotExistException() {
        super("Project not found");
    }
}
