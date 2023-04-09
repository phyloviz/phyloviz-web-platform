package org.phyloviz.pwp.administration.service.exceptions;

public class EmptyDatasetNameException extends RuntimeException {
    public EmptyDatasetNameException() {
        super("Dataset name cannot be empty");
    }
}
