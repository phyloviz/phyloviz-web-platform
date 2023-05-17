package org.phyloviz.pwp.shared.repository.metadata.distance_matrix;

import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;

import java.util.List;
import java.util.Optional;

public interface DistanceMatrixMetadataRepository {

    /**
     * Find a distance matrix metadata from a project id, dataset id and distance matrix id.
     *
     * @param projectId        the id of the project
     * @param datasetId        the id of the dataset
     * @param distanceMatrixId the id of the distance matrix resource
     * @return a distance matrix metadata
     */
    Optional<DistanceMatrixMetadata> findByProjectIdAndDatasetIdAndDistanceMatrixId(String projectId, String datasetId, String distanceMatrixId);

    /**
     * Find all distance matrix metadata from a dataset id.
     *
     * @param datasetId the id of the dataset
     * @return a list of distance matrix metadata
     */
    List<DistanceMatrixMetadata> findAllByDatasetId(String datasetId);

    /**
     * Find all distance matrix metadata from a project id and dataset id.
     *
     * @param projectId the id of the project
     * @param datasetId the id of the dataset
     * @return a list of distance matrix metadata
     */
    List<DistanceMatrixMetadata> findAllByProjectIdAndDatasetId(String projectId, String datasetId);

    /**
     * Deletes a distance matrix metadata.
     *
     * @param distanceMatrixMetadata the distance matrix metadata to delete
     */
    void delete(DistanceMatrixMetadata distanceMatrixMetadata);

    /**
     * Save a distance matrix metadata.
     *
     * @param distanceMatrixMetadata the distance matrix metadata to save
     * @return the saved distance matrix metadata
     */
    DistanceMatrixMetadata save(DistanceMatrixMetadata distanceMatrixMetadata);
}
