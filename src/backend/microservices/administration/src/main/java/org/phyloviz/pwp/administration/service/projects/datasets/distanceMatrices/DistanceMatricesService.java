package org.phyloviz.pwp.administration.service.projects.datasets.distanceMatrices;

import org.phyloviz.pwp.administration.service.dtos.distanceMatrices.DistanceMatrixDTO;
import org.phyloviz.pwp.administration.service.dtos.distanceMatrices.deleteDistanceMatrix.DeleteDistanceMatrixInputDTO;
import org.phyloviz.pwp.administration.service.dtos.distanceMatrices.deleteDistanceMatrix.DeleteDistanceMatrixOutputDTO;

/**
 * Service for operations related to distance matrices.
 */
public interface DistanceMatricesService {

    /**
     * Deletes a distance matrix.
     *
     * @param deleteDistanceMatrixInputDTO the input data for the distance matrix deletion
     * @return the output data for the distance matrix deletion
     */
    DeleteDistanceMatrixOutputDTO deleteDistanceMatrix(DeleteDistanceMatrixInputDTO deleteDistanceMatrixInputDTO);

    /**
     * Deletes a distance matrix.
     * This method is also used by other services (DatasetsService) to allow for the recursive deletion of resources.
     *
     * @param distanceMatrixId id of the distance matrix
     */
    void deleteDistanceMatrix(String distanceMatrixId);

    /**
     * Gets a distance matrix.
     * This method is also used by other services (DatasetsService) to allow for the recursive retrieval of resources.
     * Does not delete its own id from the dataset.
     *
     * @param distanceMatrixId id of the distance matrix
     * @return the distance matrix
     */
    DistanceMatrixDTO getDistanceMatrix(String distanceMatrixId);
}
