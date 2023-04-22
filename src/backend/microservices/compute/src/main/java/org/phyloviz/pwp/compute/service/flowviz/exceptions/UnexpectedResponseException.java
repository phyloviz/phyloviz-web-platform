package org.phyloviz.pwp.compute.service.flowviz.exceptions;

import lombok.Getter;
import okhttp3.Response;

public class UnexpectedResponseException extends RuntimeException {
    @Getter
    private final Response response;

    public UnexpectedResponseException(Response response) {
        super("Unexpected response: " + response);
        this.response = response;
    }
}
