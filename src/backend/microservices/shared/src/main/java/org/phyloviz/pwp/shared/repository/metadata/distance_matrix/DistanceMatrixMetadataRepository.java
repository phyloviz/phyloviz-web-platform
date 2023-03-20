package org.phyloviz.pwp.shared.repository.metadata.distance_matrix;

import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.repository.metadata.inference_tree.documents.InferenceTreeMetadata;

public interface DistanceMatrixMetadataRepository {

    /**
     * Deletes a distance matrix.
     *
     * @param distanceMatrixMetadata the distance matrix to delete
     */
    void deleteDistanceMatrix(DistanceMatrixMetadata distanceMatrixMetadata);

    /**
     * Find a distance matrix metadata from its resource id.
     *
     * @param resourceId the resource id of the distance matrix
     * @return a distance matrix metadata
     */
    DistanceMatrixMetadata findByResourceId(String resourceId);
}
