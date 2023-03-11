package uploader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Uploader Microservice of the PHYLOViZ Web Platform.
 */
@SpringBootApplication
public class UploaderMicroserviceApplication {

    /**
     * Entry point for the Uploader Microservice.
     *
     * @param args arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(UploaderMicroserviceApplication.class, args);
    }
}
