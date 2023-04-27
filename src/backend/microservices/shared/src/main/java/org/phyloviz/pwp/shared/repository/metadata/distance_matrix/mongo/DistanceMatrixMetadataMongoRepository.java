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
     * Find the first distance matrix metadata with the given id. Analogous to findAny().
     *
     * @param projectId        the id of the project
     * @param datasetId        the id of the dataset
     * @param distanceMatrixId the id of the distance matrix resource
     * @return a distance matrix metadata
     */
    Optional<DistanceMatrixMetadata> findFirstByProjectIdAndDatasetIdAndDistanceMatrixId(String projectId, String datasetId, String distanceMatrixId);

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
     * @param distanceMatrixId the id of the distance matrix resource to find metadata representations of
     * @return a list of distance matrix metadata
     */
    List<DistanceMatrixMetadata> findAllByDistanceMatrixId(String distanceMatrixId);

    /**
     * Find a distance matrix metadata from its id and repository id.
     * The repositoryId is stored as string in the document, so a custom @Query is needed.
     *
     * @param distanceMatrixId the id of the distance matrix resource
     * @param repositoryId     the id of the repository, as string, like it's stored in the document
     * @return a distance matrix metadata
     */
    @Query("{ 'distanceMatrixId' : ?0, 'repositoryId' : ?1 }")
    Optional<DistanceMatrixMetadata> findByDistanceMatrixIdAndRepositoryId(String distanceMatrixId, String repositoryId);

    /**
     * Find all distance matrix metadata from a dataset id.
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
    @Query("{ 'projectId' : ?0, 'datasetId' : ?1, 'distanceMatrixId' : ?2, 'repositoryId' : ?3 }")
    Optional<DistanceMatrixMetadata> findByProjectIdAndDatasetIdAndDistanceMatrixIdAndRepositoryId(
            String projectId, String datasetId, String distanceMatrixId, String repositoryId
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
}
