package org.phyloviz.pwp.shared.repository.metadata.distance_matrix;

import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;

import java.util.List;
import java.util.Optional;

public interface DistanceMatrixMetadataRepository {

    /**
     * Find any distance matrix metadata from its id.
     *
     * @param distanceMatrixId the id of the distance matrix resource
     * @return a distance matrix metadata
     */
    Optional<DistanceMatrixMetadata> findByDistanceMatrixId(String distanceMatrixId);

    /**
     * Find all metadata representations of a distance matrix resource.
     *
     * @param distanceMatrixId the id of the distance matrix resource
     * @return a list of distance matrix metadata
     */
    List<DistanceMatrixMetadata> findAllByDistanceMatrixId(String distanceMatrixId);

    /**
     * Find a distance matrix metadata from its id and adapter id.
     *
     * @param distanceMatrixId the id of the distance matrix resource
     * @param adapterId        the id of the adapter
     * @return a distance matrix metadata
     */
    Optional<DistanceMatrixMetadata> findByDistanceMatrixIdAndAdapterId(String distanceMatrixId, DistanceMatrixAdapterId adapterId);

    /**
     * Find all distance matrix metadata from a dataset id. Only one distance matrix metadata per distance matrix resource.
     *
     * @param datasetId the id of the dataset
     * @return a list of distance matrix metadata
     */
    List<DistanceMatrixMetadata> findAllByDatasetId(String datasetId);

    /**
     * Deletes a distance matrix metadata.
     *
     * @param distanceMatrixMetadata the distance matrix metadata to delete
     */
    void delete(DistanceMatrixMetadata distanceMatrixMetadata);
}
