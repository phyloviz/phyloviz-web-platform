package org.phyloviz.pwp.shared.service.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("User is not authorized to perform this action.");
    }
}
