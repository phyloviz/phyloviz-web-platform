package org.phyloviz.pwp.shared.repository.metadata.distanceMatrix;

import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.DistanceMatrixMetadata;

import java.util.List;

public interface DistanceMatrixMetadataRepository {

    /**
     * Deletes a distance matrix metadata.
     *
     * @param distanceMatrixMetadata the distance matrix metadata to delete
     */
    void delete(DistanceMatrixMetadata distanceMatrixMetadata);

    /**
     * Find a distance matrix metadata from its id.
     *
     * @param distanceMatrixId the id of the distance matrix resource
     * @return a distance matrix metadata
     */
    DistanceMatrixMetadata findByDistanceMatrixId(String distanceMatrixId);

    /**
     * Find all metadata representations of a distance matrix resource.
     *
     * @param distanceMatrixId the id of the distance matrix resource
     * @return a list of distance matrix metadata
     */
    List<DistanceMatrixMetadata> findAllByResourceId(String distanceMatrixId);
}
