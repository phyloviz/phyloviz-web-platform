package org.phyloviz.pwp.compute.service.flowviz;

import org.phyloviz.pwp.compute.service.flowviz.exceptions.AuthenticationException;
import org.phyloviz.pwp.compute.service.flowviz.identity.Credentials;

/**
 * Builder for the {@link FLOWViZClient}.
 */
public class FLOWViZClientBuilder {
    private String baseUrl;
    private Credentials credentials;

    /**
     * Set the credentials to authenticate with.
     *
     * @param username the username
     * @param password the password
     * @return the builder
     */
    public FLOWViZClientBuilder credentials(String username, String password) {
        this.credentials = new Credentials(username, password);
        return this;
    }

    /**
     * Set the base URL of the FLOWViZ API.
     *
     * @param baseUrl the base URL
     * @return the builder
     */
    public FLOWViZClientBuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * Build the {@link FLOWViZClient}.
     *
     * @return the client
     * @throws AuthenticationException if the credentials are invalid
     */
    public FLOWViZClient authenticate() throws AuthenticationException {
        if (baseUrl == null)
            throw new IllegalStateException("baseUrl is required");

        if (credentials == null)
            throw new IllegalStateException("credentials are required");

        return new FLOWViZClient(baseUrl, credentials);
    }
}
