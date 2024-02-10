package org.phyloviz.pwp.service.exceptions;

public class TypingDataDoesNotExistException extends RuntimeException {
    public TypingDataDoesNotExistException() {
        super("Typing data not found");
    }
}
