package org.phyloviz.pwp.shared.service.exceptions;

public class TreeViewNotFoundException extends RuntimeException {
    public TreeViewNotFoundException() {
        super("Tree view not found");
    }
}
