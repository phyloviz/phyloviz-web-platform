package org.phyloviz.pwp.service.exceptions;

public class DeniedResourceDeletionException extends RuntimeException {
    public DeniedResourceDeletionException(String msg) {
        super(msg);
    }
}
