package org.phyloviz.pwp.shared.service.exceptions;

public class DatasetNotFoundException extends RuntimeException {
    public DatasetNotFoundException() {
        super("Dataset not found");
    }
}
