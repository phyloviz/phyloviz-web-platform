package org.phyloviz.pwp.administration.service.exceptions;

public class IsolateDataDoesNotExistException extends RuntimeException {
    public IsolateDataDoesNotExistException() {
        super("Isolate data not found");
    }
}
