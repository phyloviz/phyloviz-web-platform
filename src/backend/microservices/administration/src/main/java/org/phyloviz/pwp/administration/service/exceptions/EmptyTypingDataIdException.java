package org.phyloviz.pwp.administration.service.exceptions;

public class EmptyTypingDataIdException extends RuntimeException {
    public EmptyTypingDataIdException() {
        super("Typing data id cannot be empty");
    }
}
