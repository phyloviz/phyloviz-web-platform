package org.phyloviz.pwp.compute.service.flowviz.exceptions;

import java.io.IOException;

public class ConnectionRefusedException extends RuntimeException {
    public ConnectionRefusedException(IOException e) {
        super("Connection refused", e);
    }
}
