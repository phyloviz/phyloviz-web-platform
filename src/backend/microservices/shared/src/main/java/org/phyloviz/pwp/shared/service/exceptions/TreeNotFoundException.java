package org.phyloviz.pwp.shared.service.exceptions;

public class TreeNotFoundException extends RuntimeException {
    public TreeNotFoundException() {
        super("Tree not found");
    }
}
