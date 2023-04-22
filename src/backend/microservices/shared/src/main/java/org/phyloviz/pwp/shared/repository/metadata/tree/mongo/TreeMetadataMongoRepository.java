package org.phyloviz.pwp.shared.repository.metadata.tree.mongo;

import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TreeMetadataMongoRepository extends MongoRepository<TreeMetadata, String> {

    /**
     * Find a tree metadata from its id.
     *
     * @param resourceId the id of the tree resource
     * @return an inference tree metadata
     */
    Optional<TreeMetadata> findByTreeId(String resourceId);

    /**
     * Find all metadata representations of a tree resource.
     *
     * @param treeId the id of the tree resource
     * @return a list of tree metadata
     */
    List<TreeMetadata> findAllByTreeId(String treeId);

    /**
     * Find a tree metadata from its id and adapter id.
     * The adapterId is stored as string in the document, so a custom @Query is needed.
     *
     * @param treeId    the id of the tree resource
     * @param adapterId the id of the adapter, as string, like it's stored in the document
     * @return a tree metadata
     */
    @Query("{ 'treeId' : ?0, 'adapterId' : ?1 }")
    Optional<TreeMetadata> findByTreeIdAndAdapterId(String treeId, String adapterId);

    @Query("{ 'projectId' : ?0, 'datasetId' : ?1, 'treeViewId' : ?2, 'adapterId' : ?3 }")
    Optional<TreeMetadata> findByProjectIdAndDatasetIdAndTreeViewIdAndAdapterId(
            String projectId, String datasetId, String treeViewId, String adapterId
    );

    List<TreeMetadata> findAllByProjectIdAndDatasetId(String projectId, String datasetId);

    /**
     * Find all tree metadata from a dataset id.
     *
     * @param datasetId the id of the dataset
     * @return a list of tree metadata
     */
    List<TreeMetadata> findAllByDatasetId(String datasetId);

    @Query("{ 'datasetId' : ?0, 'source' : { 'distanceMatrixId' : ?1 } }")
    boolean existsByDatasetIdAndDistanceMatrixIdSource(String datasetId, String distanceMatrixId);

    boolean existsByProjectIdAndDatasetIdAndTreeId(String projectId, String datasetId, String treeId);
}
