package org.phyloviz.pwp.http.controllers.projects.datasets.distance_matrices;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.http.models.distance_matrices.delete_distance_matrix.DeleteDistanceMatrixOutputModel;
import org.phyloviz.pwp.http.models.distance_matrices.update_distance_matrix.UpdateDistanceMatrixInputModel;
import org.phyloviz.pwp.http.models.distance_matrices.update_distance_matrix.UpdateDistanceMatrixOutputModel;
import org.phyloviz.pwp.service.dtos.distance_matrix.UpdateDistanceMatrixOutput;
import org.phyloviz.pwp.service.project.dataset.distance_matrix.DistanceMatrixService;
import org.phyloviz.pwp.domain.User;
import org.springframework.web.bind.annotation.*;

/**
 * Controller that handles requests related to distance matrices.
 */
@RestController
@RequiredArgsConstructor
public class DistanceMatricesController {

    private final DistanceMatrixService distanceMatrixService;

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
        distanceMatrixService.deleteDistanceMatrix(projectId, datasetId, distanceMatrixId, user.getId());

        return new DeleteDistanceMatrixOutputModel(projectId, datasetId, distanceMatrixId);
    }

    /**
     * Updates a distance matrix.
     *
     * @param projectId        the id of the project that contains the distance matrix
     * @param datasetId        the id of the dataset that contains the distance matrix
     * @param distanceMatrixId the id of the distance matrix to be updated
     * @param user             the user that is updating the distance matrix
     * @return information about the updated distance matrix
     */
    @PatchMapping("/projects/{projectId}/datasets/{datasetId}/distance-matrices/{distanceMatrixId}")
    public UpdateDistanceMatrixOutputModel updateDistanceMatrix(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            @PathVariable String distanceMatrixId,
            @RequestBody UpdateDistanceMatrixInputModel updateDistanceMatrixInputModel,
            User user
    ) {
        UpdateDistanceMatrixOutput updateDistanceMatrixOutput = distanceMatrixService.updateDistanceMatrix(
                updateDistanceMatrixInputModel.getName(), projectId, datasetId, distanceMatrixId, user.getId()
        );

        return new UpdateDistanceMatrixOutputModel(updateDistanceMatrixOutput);
    }
}
