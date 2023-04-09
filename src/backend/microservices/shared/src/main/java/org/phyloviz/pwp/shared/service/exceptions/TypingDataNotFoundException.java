package org.phyloviz.pwp.shared.service.exceptions;

public class TypingDataNotFoundException extends RuntimeException {
    public TypingDataNotFoundException() {
        super("Typing data not found");
    }
}
