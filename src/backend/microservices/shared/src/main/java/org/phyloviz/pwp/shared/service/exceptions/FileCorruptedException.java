package org.phyloviz.pwp.shared.service.exceptions;

public class FileCorruptedException extends RuntimeException {
    public FileCorruptedException(String message) {
        super(message);
    }
}
