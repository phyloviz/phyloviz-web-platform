package org.phyloviz.pwp.shared.service.exceptions;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(String msg) {
        super(msg);
    }
}
