package org.phyloviz.pwp.shared.service.exceptions;

public class TypingDataDoesNotExistException extends RuntimeException {
    public TypingDataDoesNotExistException() {
        super("Typing data not found");
    }
}
