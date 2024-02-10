package org.phyloviz.pwp.service.exceptions;

public class MultipartFileReadException extends RuntimeException {
    public MultipartFileReadException(String msg, Throwable e) {
        super(msg, e);
    }
}
