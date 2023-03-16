package org.phyloviz.pwp.visualization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Visualization Microservice of the PHYLOViZ Web Platform.
 */
@SpringBootApplication
public class VisualizationMicroserviceApplication {

    /**
     * Entry point for the Visualization Microservice.
     *
     * @param args arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(VisualizationMicroserviceApplication.class, args);
    }
}
