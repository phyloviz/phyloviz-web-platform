package org.phyloviz.pwp.shared.service.exceptions;

public class EmptyTypingDataIdException extends RuntimeException {
    public EmptyTypingDataIdException() {
        super("Typing data id cannot be empty");
    }
}
