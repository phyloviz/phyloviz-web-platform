package org.phyloviz.pwp.service.exceptions;

public class IsolateDataNotFoundException extends RuntimeException {
    public IsolateDataNotFoundException() {
        super("Isolate data not found");
    }
}
