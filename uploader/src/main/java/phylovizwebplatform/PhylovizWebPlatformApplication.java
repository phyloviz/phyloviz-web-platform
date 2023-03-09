package phylovizwebplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * Main class for the Phyloviz Web Platform application.
 */
@SpringBootApplication
public class PhylovizWebPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhylovizWebPlatformApplication.class, args);
    }
}
