package org.phyloviz.pwp.shared.repository.metadata.distance_matrix;

import org.phyloviz.pwp.shared.repository.data.distance_matrix.DistanceMatrixDataRepositoryId;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;

import java.util.List;
import java.util.Optional;

public interface DistanceMatrixMetadataRepository {

    /**
     * Find any distance matrix metadata from a project id, dataset id and distance matrix id.
     *
     * @param projectId        the id of the project
     * @param datasetId        the id of the dataset
     * @param distanceMatrixId the id of the distance matrix resource
     * @return a distance matrix metadata
     */
    Optional<DistanceMatrixMetadata> findAnyByProjectIdAndDatasetIdAndDistanceMatrixId(String projectId, String datasetId, String distanceMatrixId);

    /**
     * Find all distance matrix metadata from a project id, dataset id and distance matrix id.
     *
     * @param projectId        the id of the project
     * @param datasetId        the id of the dataset
     * @param distanceMatrixId the id of the distance matrix
     * @return a list of distance matrix metadata
     */
    List<DistanceMatrixMetadata> findAllByProjectIdAndDatasetIdAndDistanceMatrixId(String projectId, String datasetId, String distanceMatrixId);

    /**
     * Find all metadata representations of a distance matrix resource.
     *
     * @param distanceMatrixId the id of the distance matrix resource
     * @return a list of distance matrix metadata
     */
    List<DistanceMatrixMetadata> findAllByDistanceMatrixId(String distanceMatrixId);

    /**
     * Find all distance matrix metadata from a dataset id. Only one distance matrix metadata per distance matrix resource.
     *
     * @param datasetId the id of the dataset
     * @return a list of distance matrix metadata
     */
    List<DistanceMatrixMetadata> findAllByDatasetId(String datasetId);

    /**
     * Find a distance matrix metadata from a project id, dataset id, distance matrix id and repository id.
     *
     * @param projectId        the id of the project
     * @param datasetId        the id of the dataset
     * @param distanceMatrixId the id of the distance matrix
     * @param repositoryId     the id of the repository
     * @return a distance matrix metadata
     */
    Optional<DistanceMatrixMetadata> findByProjectIdAndDatasetIdAndDistanceMatrixIdAndRepositoryId(
            String projectId, String datasetId, String distanceMatrixId, DistanceMatrixDataRepositoryId repositoryId
    );

    List<DistanceMatrixMetadata> findAllByProjectIdAndDatasetId(String projectId, String datasetId);

    /**
     * Check if a distance matrix metadata exists from a project id, dataset id and distance matrix id.
     *
     * @param projectId        the id of the project
     * @param datasetId        the id of the dataset
     * @param distanceMatrixId the id of the distance matrix
     * @return true if exists, false otherwise
     */
    boolean existsByProjectIdAndDatasetIdAndDistanceMatrixId(String projectId, String datasetId, String distanceMatrixId);

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
