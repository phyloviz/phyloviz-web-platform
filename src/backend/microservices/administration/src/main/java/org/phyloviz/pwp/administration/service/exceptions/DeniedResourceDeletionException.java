package org.phyloviz.pwp.administration.service.exceptions;

public class DeniedResourceDeletionException extends RuntimeException {
    public DeniedResourceDeletionException(String msg) {
        super(msg);
    }
}
