package org.phyloviz.pwp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the PHYLOViZ Web Platform.
 */
@SpringBootApplication
public class PWPApplication {

    /**
     * Entry point for the PHYLOViZ Web Platform.
     *
     * @param args arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(PWPApplication.class, args);
    }
}
