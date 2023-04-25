package org.phyloviz.pwp.shared.repository.metadata.distance_matrix;

import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAdapterId;
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
     * Find a distance matrix metadata from a project id, dataset id, distance matrix id and adapter id.
     *
     * @param projectId        the id of the project
     * @param datasetId        the id of the dataset
     * @param distanceMatrixId the id of the distance matrix
     * @param adapterId        the id of the adapter
     * @return a distance matrix metadata
     */
    Optional<DistanceMatrixMetadata> findByProjectIdAndDatasetIdAndDistanceMatrixIdAndAdapterId(
            String projectId, String datasetId, String distanceMatrixId, DistanceMatrixAdapterId adapterId
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
     * @param distanceMatrixMetadata the distance matrix metadata to save
     * @return the saved distance matrix metadata
     */
    DistanceMatrixMetadata save(DistanceMatrixMetadata distanceMatrixMetadata);
}
