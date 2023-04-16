package org.phyloviz.pwp.shared.service.exceptions;

public class DeniedFileDeletionException extends RuntimeException {
    public DeniedFileDeletionException(String msg) {
        super(msg);
    }
}
