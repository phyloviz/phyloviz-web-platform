package org.phyloviz.pwp.service.exceptions;

public class DatasetDoesNotExistException extends RuntimeException {
    public DatasetDoesNotExistException() {
        super("Dataset not found");
    }
}
