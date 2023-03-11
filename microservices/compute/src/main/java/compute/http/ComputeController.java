package compute.http;

import compute.service.ComputeService;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the Compute Microservice.
 */
@RestController
public class ComputeController {

    private final ComputeService computeService;

    public ComputeController(ComputeService computeService) {
        this.computeService = computeService;
    }

    // TODO: 11/03/2023 To be implemented.
}
