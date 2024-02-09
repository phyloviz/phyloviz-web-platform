package org.phyloviz.pwp.service.exceptions;

public class DistanceMatrixNotFoundException extends RuntimeException {
    public DistanceMatrixNotFoundException() {
        super("Distance matrix not found");
    }
}
