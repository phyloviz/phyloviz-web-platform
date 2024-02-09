package org.phyloviz.pwp.service.exceptions;

public class TreeDoesNotExistException extends RuntimeException {
    public TreeDoesNotExistException() {
        super("Tree not found");
    }
}
