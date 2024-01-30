package org.phyloviz.pwp.service.exceptions;

public class ProjectDoesNotExistException extends RuntimeException {
    public ProjectDoesNotExistException() {
        super("Project not found");
    }
}
