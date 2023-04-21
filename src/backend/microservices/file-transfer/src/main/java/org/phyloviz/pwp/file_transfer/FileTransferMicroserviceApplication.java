package org.phyloviz.pwp.file_transfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the FileTransfer Microservice of the PHYLOViZ Web Platform.
 */
@SpringBootApplication
public class FileTransferMicroserviceApplication {

    /**
     * Entry point for the FileTransfer Microservice.
     *
     * @param args arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(FileTransferMicroserviceApplication.class, args);
    }
}
