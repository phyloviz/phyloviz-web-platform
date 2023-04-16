package org.phyloviz.pwp.shared.service.exceptions;

public class DeniedResourceDeletionException extends RuntimeException {
    public DeniedResourceDeletionException(String msg) {
        super(msg);
    }
}
