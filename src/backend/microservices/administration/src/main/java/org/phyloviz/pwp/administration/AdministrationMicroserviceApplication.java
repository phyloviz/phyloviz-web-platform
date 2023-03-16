package org.phyloviz.pwp.administration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Administration Microservice of the PHYLOViZ Web Platform.
 */
@SpringBootApplication
public class AdministrationMicroserviceApplication {

    /**
     * Entry point for the Administration Microservice.
     *
     * @param args arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(AdministrationMicroserviceApplication.class, args);
    }
}
