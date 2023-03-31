package org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.mongo;

import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.DistanceMatrixMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistanceMatrixMetadataMongoRepository extends MongoRepository<DistanceMatrixMetadata, String> {

    /**
     * Find a distance matrix metadata from its resource id.
     *
     * @param resourceId the resource id of the distance matrix
     * @return a distance matrix metadata
     */
    DistanceMatrixMetadata findByDistanceMatrixId(String resourceId);

    /**
     * Find all metadata representations of a distance matrix resource.
     *
     * @param resourceId the id of the distance matrix resource to find metadata representations of
     * @return a list of distance matrix metadata
     */
    List<DistanceMatrixMetadata> findAllByDistanceMatrixId(String resourceId);
}
