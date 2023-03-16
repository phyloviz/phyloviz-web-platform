package org.phyloviz.pwp.administration.service.exceptions;

public class UnauthorizedExcception extends RuntimeException {
    public UnauthorizedExcception(String msg) {
        super(msg);
    }
}
