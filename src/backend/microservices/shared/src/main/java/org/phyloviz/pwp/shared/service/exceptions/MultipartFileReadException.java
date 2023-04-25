package org.phyloviz.pwp.shared.service.exceptions;

public class MultipartFileReadException extends RuntimeException {
    public MultipartFileReadException(String msg, Throwable e) {
        super(msg, e);
    }
}
