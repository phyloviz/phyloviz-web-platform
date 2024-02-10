package org.phyloviz.pwp.service.exceptions;

public class DeniedFileDeletionException extends RuntimeException {
    public DeniedFileDeletionException(String msg) {
        super(msg);
    }
}
