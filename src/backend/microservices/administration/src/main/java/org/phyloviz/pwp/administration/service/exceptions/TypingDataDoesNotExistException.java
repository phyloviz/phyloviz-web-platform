package org.phyloviz.pwp.administration.service.exceptions;

public class TypingDataDoesNotExistException extends RuntimeException {
    public TypingDataDoesNotExistException() {
        super("Typing data not found");
    }
}
