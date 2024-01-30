package org.phyloviz.pwp.service.project.dataset.distance_matrix;

import org.phyloviz.pwp.service.dtos.distance_matrix.DistanceMatrixInfo;
import org.phyloviz.pwp.service.dtos.distance_matrix.UpdateDistanceMatrixOutput;

import java.util.List;

/**
 * Service for managing distance matrices.
 */
public interface DistanceMatrixService {

    /**
     * Returns a list of distance matrix infos for the given dataset.
     *
     * @param datasetId the dataset id
     * @return a list of distance matrix infos for the given dataset
     */
    List<DistanceMatrixInfo> getDistanceMatrixInfos(String datasetId);

    /**
     * Deletes a distance matrix.
     *
     * @param projectId        the project id
     * @param datasetId        the dataset id
     * @param distanceMatrixId the distance matrix id
     * @param userId           the user id
     */
    void deleteDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId);

    /**
     * Deletes all distance matrices of a dataset.
     *
     * @param projectId the project id
     * @param datasetId the dataset id
     */
    void deleteAllByProjectIdAndDatasetId(String projectId, String datasetId);

    /**
     * Updates a distance matrix.
     *
     * @param name             the name of the distance matrix
     * @param projectId        the project id
     * @param datasetId        the dataset id
     * @param distanceMatrixId the distance matrix id
     * @param userId           the user id
     * @return the output of the distance matrix update
     */
    UpdateDistanceMatrixOutput updateDistanceMatrix(String name, String projectId, String datasetId, String distanceMatrixId, String userId);
}
