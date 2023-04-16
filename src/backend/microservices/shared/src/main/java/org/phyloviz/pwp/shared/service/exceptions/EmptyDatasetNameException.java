package org.phyloviz.pwp.shared.service.exceptions;

public class EmptyDatasetNameException extends RuntimeException {
    public EmptyDatasetNameException() {
        super("Dataset name cannot be empty");
    }
}
