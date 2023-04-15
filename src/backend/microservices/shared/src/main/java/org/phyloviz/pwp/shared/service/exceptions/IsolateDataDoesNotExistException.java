package org.phyloviz.pwp.shared.service.exceptions;

public class IsolateDataDoesNotExistException extends RuntimeException {
    public IsolateDataDoesNotExistException() {
        super("Isolate data not found");
    }
}
