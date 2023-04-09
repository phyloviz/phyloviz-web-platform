package org.phyloviz.pwp.compute.service.flowviz;

import org.phyloviz.pwp.compute.service.flowviz.exceptions.AuthenticationException;
import org.phyloviz.pwp.compute.service.flowviz.identity.Credentials;

public class FlowVizClientBuilder {
    private String baseUrl;
    private Credentials credentials;

    public FlowVizClientBuilder credentials(String username, String password) {
        this.credentials = new Credentials(username, password);
        return this;
    }

    public FlowVizClientBuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public FlowVizClient authenticate() throws AuthenticationException {
        if (baseUrl == null)
            throw new IllegalStateException("baseUrl is required");

        if (credentials == null)
            throw new IllegalStateException("credentials are required");


        return new FlowVizClient(baseUrl, credentials);
    }
}
