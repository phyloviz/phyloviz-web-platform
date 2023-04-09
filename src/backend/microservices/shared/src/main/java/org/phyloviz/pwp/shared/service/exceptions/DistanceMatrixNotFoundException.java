package org.phyloviz.pwp.shared.service.exceptions;

public class DistanceMatrixNotFoundException extends RuntimeException {
    public DistanceMatrixNotFoundException() {
        super("Distance matrix not found");
    }
}
