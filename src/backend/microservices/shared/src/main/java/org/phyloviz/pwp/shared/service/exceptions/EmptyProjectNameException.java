package org.phyloviz.pwp.shared.service.exceptions;

public class EmptyProjectNameException extends RuntimeException {
    public EmptyProjectNameException() {
        super("Project name cannot be empty");
    }
}
