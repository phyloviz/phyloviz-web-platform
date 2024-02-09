package org.phyloviz.pwp.service.exceptions;

public class TreeViewDoesNotExistException extends RuntimeException {
    public TreeViewDoesNotExistException() {
        super("Tree view not found");
    }
}
