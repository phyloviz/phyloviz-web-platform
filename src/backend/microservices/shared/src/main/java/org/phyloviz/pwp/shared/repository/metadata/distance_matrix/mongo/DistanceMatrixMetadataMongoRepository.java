package org.phyloviz.pwp.shared.repository.metadata.distance_matrix.mongo;

import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistanceMatrixMetadataMongoRepository extends MongoRepository<DistanceMatrixMetadata, String> {

    /**
     * Find a distance matrix metadata from its id.
     *
     * @param distanceMatrixId the id of the distance matrix resource
     * @return a distance matrix metadata
     */
    Optional<DistanceMatrixMetadata> findByDistanceMatrixId(String distanceMatrixId);

    /**
     * Find all metadata representations of a distance matrix resource.
     *
     * @param distanceMatrixId the id of the distance matrix resource to find metadata representations of
     * @return a list of distance matrix metadata
     */
    List<DistanceMatrixMetadata> findAllByDistanceMatrixId(String distanceMatrixId);

    /**
     * Find a distance matrix metadata from its id and adapter id.
     * The adapterId is stored as string in the document, so a custom @Query is needed.
     *
     * @param distanceMatrixId the id of the distance matrix resource
     * @param adapterId        the id of the adapter, as string, like it's stored in the document
     * @return a distance matrix metadata
     */
    @Query("{ 'distanceMatrixId' : ?0, 'adapterId' : ?1 }")
    Optional<DistanceMatrixMetadata> findByDistanceMatrixIdAndAdapterId(String distanceMatrixId, String adapterId);

    /**
     * Find all distance matrix metadata from a dataset id.
     *
     * @param datasetId the id of the dataset
     * @return a list of distance matrix metadata
     */
    List<DistanceMatrixMetadata> findAllByDatasetId(String datasetId);
}
