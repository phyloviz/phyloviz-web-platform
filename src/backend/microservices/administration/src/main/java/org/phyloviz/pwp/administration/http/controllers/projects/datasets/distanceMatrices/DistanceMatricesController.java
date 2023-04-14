package org.phyloviz.pwp.administration.http.controllers.projects.datasets.distanceMatrices;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.http.models.distanceMatrices.deleteDistanceMatrix.DeleteDistanceMatrixOutputModel;
import org.phyloviz.pwp.administration.service.dtos.distanceMatrices.deleteDistanceMatrix.DeleteDistanceMatrixInputDTO;
import org.phyloviz.pwp.administration.service.dtos.distanceMatrices.deleteDistanceMatrix.DeleteDistanceMatrixOutputDTO;
import org.phyloviz.pwp.administration.service.projects.datasets.distanceMatrices.DistanceMatricesService;
import org.phyloviz.pwp.shared.domain.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles requests related to distance matrices.
 */
@RestController
@RequiredArgsConstructor
public class DistanceMatricesController {

    private final DistanceMatricesService distanceMatricesService;

    /**
     * Deletes a distance matrix.
     *
     * @param projectId        the id of the project that contains the distance matrix
     * @param datasetId        the id of the dataset that contains the distance matrix
     * @param distanceMatrixId the id of the distance matrix to be deleted
     * @param user             the user that is deleting the distance matrix
     * @return information about the deleted distance matrix
     */
    @DeleteMapping("/projects/{projectId}/datasets/{datasetId}/distance-matrices/{distanceMatrixId}")
    public DeleteDistanceMatrixOutputModel deleteDistanceMatrix(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            @PathVariable String distanceMatrixId,
            User user
    ) {
        DeleteDistanceMatrixOutputDTO deleteDistanceMatrixOutputDTO = distanceMatricesService.deleteDistanceMatrix(
                new DeleteDistanceMatrixInputDTO(projectId, datasetId, distanceMatrixId, user.toDTO())
        );

        return new DeleteDistanceMatrixOutputModel(deleteDistanceMatrixOutputDTO);
    }
}
