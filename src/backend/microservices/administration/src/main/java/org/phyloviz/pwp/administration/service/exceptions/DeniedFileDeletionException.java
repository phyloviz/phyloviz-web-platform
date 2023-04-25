package org.phyloviz.pwp.administration.service.exceptions;

public class DeniedFileDeletionException extends RuntimeException {
    public DeniedFileDeletionException(String msg) {
        super(msg);
    }
}
