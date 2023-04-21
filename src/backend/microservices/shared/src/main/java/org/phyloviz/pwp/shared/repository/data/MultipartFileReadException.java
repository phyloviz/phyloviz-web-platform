package org.phyloviz.pwp.shared.repository.data;

public class MultipartFileReadException extends RuntimeException {
    public MultipartFileReadException(String msg, Throwable e) {
        super(msg, e);
    }
}
