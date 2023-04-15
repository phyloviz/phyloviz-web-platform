package org.phyloviz.pwp.compute.service.exceptions;

public class DatasetDoesNotExistException extends RuntimeException {
    public DatasetDoesNotExistException() {
        super("Dataset not found");
    }
}
