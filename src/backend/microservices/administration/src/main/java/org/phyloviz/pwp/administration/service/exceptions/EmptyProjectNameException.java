package org.phyloviz.pwp.administration.service.exceptions;

public class EmptyProjectNameException extends RuntimeException {
    public EmptyProjectNameException() {
        super("Project name cannot be empty");
    }
}
