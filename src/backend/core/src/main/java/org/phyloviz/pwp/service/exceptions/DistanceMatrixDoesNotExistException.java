package org.phyloviz.pwp.service.exceptions;

public class DistanceMatrixDoesNotExistException extends RuntimeException {
    public DistanceMatrixDoesNotExistException() {
        super("Distance matrix not found");
    }
}
