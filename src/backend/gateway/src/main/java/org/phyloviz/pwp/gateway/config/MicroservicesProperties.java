package org.phyloviz.pwp.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Map;

/**
 * Configuration for the microservices of the application.
 */
@Configuration
@ConfigurationProperties(prefix = "pwp")
public class MicroservicesProperties {
    private Map<String, MicroserviceProperties> microservices;

    public Map<String, MicroserviceProperties> getMicroservices() {
        return microservices;
    }

    public void setMicroservices(Map<String, MicroserviceProperties> microservices) {
        this.microservices = microservices;
    }

    @Data
    public static class MicroserviceProperties {
        private final String uri;
        private final List<ApiRoute> routes;
    }

    @Data
    public static class ApiRoute {
        private final HttpMethod method;
        private final String path;
    }
}