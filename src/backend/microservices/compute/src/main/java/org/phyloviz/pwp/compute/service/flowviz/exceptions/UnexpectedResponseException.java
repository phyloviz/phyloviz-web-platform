package org.phyloviz.pwp.compute.service.flowviz.exceptions;

import okhttp3.Response;

public class UnexpectedResponseException extends RuntimeException {
    Response response;

    public UnexpectedResponseException(Response response) {
        super("Unexpected response: " + response);
        this.response = response;
    }
}
