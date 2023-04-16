package org.phyloviz.pwp.compute.service.exceptions;

public class TreeDoesNotExistException extends RuntimeException {
    public TreeDoesNotExistException() {
        super("Tree not found");
    }
}
