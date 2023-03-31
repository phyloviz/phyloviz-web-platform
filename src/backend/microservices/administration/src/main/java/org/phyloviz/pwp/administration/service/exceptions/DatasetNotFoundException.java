package org.phyloviz.pwp.administration.service.exceptions;

public class DatasetNotFoundException extends RuntimeException {
    public DatasetNotFoundException() {
        super("Dataset not found");
    }
}
