package org.phyloviz.pwp.compute.service.exceptions;

public class DistanceMatrixDoesNotExistException extends RuntimeException {
    public DistanceMatrixDoesNotExistException() {
        super("Distance matrix not found");
    }
}
