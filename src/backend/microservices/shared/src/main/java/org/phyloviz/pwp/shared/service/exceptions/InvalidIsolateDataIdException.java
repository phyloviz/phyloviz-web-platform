package org.phyloviz.pwp.shared.service.exceptions;

public class InvalidIsolateDataIdException extends RuntimeException {
    public InvalidIsolateDataIdException() {
        super("Isolate data id is invalid.");
    }
}
