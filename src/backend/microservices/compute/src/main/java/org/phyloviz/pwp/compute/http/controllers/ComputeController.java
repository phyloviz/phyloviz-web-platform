package org.phyloviz.pwp.compute.http.controllers;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.compute.http.controllers.models.computeDistanceMatrix.ComputeDistanceMatrixInputModel;
import org.phyloviz.pwp.compute.http.controllers.models.computeDistanceMatrix.ComputeDistanceMatrixOutputModel;
import org.phyloviz.pwp.compute.service.ComputeService;
import org.phyloviz.pwp.compute.service.dtos.computeDistanceMatrix.ComputeDistanceMatrixOutputDTO;
import org.phyloviz.pwp.shared.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the Compute Microservice.
 */
@RestController
@RequiredArgsConstructor
public class ComputeController {

    private final ComputeService computeService;


    // TODO: 11/03/2023 To be implemented.
    @PostMapping("/distance-matrix")
    public ComputeDistanceMatrixOutputModel computeDistanceMatrix(
            @RequestBody ComputeDistanceMatrixInputModel inputModel,
            User user
    ) {
        ComputeDistanceMatrixOutputDTO computeDistanceMatrixOutputDTO = computeService.computeDistanceMatrix(inputModel.getProjectId(), inputModel.getTypingDatasetId(), user.toDTO());

        return new ComputeDistanceMatrixOutputModel(computeDistanceMatrixOutputDTO);
    }
}
