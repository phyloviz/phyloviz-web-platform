package org.phyloviz.pwp.repository.metadata.tree.mongo;

import org.phyloviz.pwp.repository.metadata.tree.documents.TreeMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TreeMetadataMongoRepository extends MongoRepository<TreeMetadata, String> {

    /**
     * Find the first tree metadata with the given id. Analogous to findAny().
     *
     * @param treeId the id of the tree resource
     * @return a tree metadata
     */
    Optional<TreeMetadata> findByProjectIdAndDatasetIdAndTreeId(String projectId, String datasetId, String treeId);

    /**
     * Find all tree metadata from a project id and dataset id.
     *
     * @param projectId the id of the project
     * @param datasetId the id of the dataset
     * @return a list of tree metadata
     */
    List<TreeMetadata> findAllByProjectIdAndDatasetId(String projectId, String datasetId);

    /**
     * Find all tree metadata from a dataset id.
     *
     * @param datasetId the id of the dataset
     * @return a list of tree metadata
     */
    List<TreeMetadata> findAllByDatasetId(String datasetId);

    /**
     * Find a tree metadata with dataset id and its source is a distance matrix of distance matrix id.
     *
     * @param datasetId        the id of the dataset
     * @param distanceMatrixId the id of the distance matrix
     * @return the tree metadata
     */
    @Query("{ 'datasetId' : ?0, 'source.distanceMatrixId' : ?1 }")
    List<TreeMetadata> findByDatasetIdAndDistanceMatrixIdSource(String datasetId, String distanceMatrixId);
}
