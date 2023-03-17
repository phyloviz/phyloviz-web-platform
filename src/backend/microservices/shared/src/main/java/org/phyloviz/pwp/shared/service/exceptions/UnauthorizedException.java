package org.phyloviz.pwp.shared.service.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }
}
