package org.phyloviz.pwp.service.exceptions;

public class DatasetNotFoundException extends RuntimeException {
    public DatasetNotFoundException() {
        super("Dataset not found");
    }
}
