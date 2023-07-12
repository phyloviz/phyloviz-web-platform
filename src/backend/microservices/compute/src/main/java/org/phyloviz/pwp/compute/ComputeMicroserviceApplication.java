package org.phyloviz.pwp.compute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Compute Microservice of the PHYLOViZ Web Platform.
 */
@SpringBootApplication
public class ComputeMicroserviceApplication {

    /**
     * Entry point for the Compute Microservice.
     *
     * @param args arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(ComputeMicroserviceApplication.class, args);
    }
}
