package org.phyloviz.pwp.shared.repository.metadata.distance_matrix.mongo;

import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DistanceMatrixMetadataMongoRepository extends MongoRepository<DistanceMatrixMetadata, String> {

    /**
     * Find a distance matrix metadata from its resource id.
     *
     * @param resourceId the resource id of the distance matrix
     * @return a distance matrix metadata
     */
    DistanceMatrixMetadata findByResourceId(String resourceId);
}
