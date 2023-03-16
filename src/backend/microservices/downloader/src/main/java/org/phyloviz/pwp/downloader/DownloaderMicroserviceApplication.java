package org.phyloviz.pwp.downloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Downloader Microservice of the PHYLOViZ Web Platform.
 */
@SpringBootApplication
public class DownloaderMicroserviceApplication {

    /**
     * Entry point for the Downloader Microservice.
     *
     * @param args arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(DownloaderMicroserviceApplication.class, args);
    }
}
