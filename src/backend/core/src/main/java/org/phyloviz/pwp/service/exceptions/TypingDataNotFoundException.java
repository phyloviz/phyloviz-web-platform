package org.phyloviz.pwp.service.exceptions;

public class TypingDataNotFoundException extends RuntimeException {
    public TypingDataNotFoundException() {
        super("Typing data not found");
    }
}
