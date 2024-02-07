package org.phyloviz.pwp.service.exceptions;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException() {
        super("Project not found");
    }
}
