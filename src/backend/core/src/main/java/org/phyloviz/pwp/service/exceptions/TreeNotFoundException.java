package org.phyloviz.pwp.service.exceptions;

public class TreeNotFoundException extends RuntimeException {
    public TreeNotFoundException() {
        super("Tree not found");
    }
}
