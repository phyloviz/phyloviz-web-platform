package org.phyloviz.pwp.shared.repository.metadata.distance_matrix.mongo;

import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistanceMatrixMetadataMongoRepository extends MongoRepository<DistanceMatrixMetadata, String> {

    /**
     * Find the first distance matrix metadata with the given id. Analogous to findAny().
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
}
