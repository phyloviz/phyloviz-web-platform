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
     * Find the first tree metadata with the given id. Analogous to findAny().
     *
     * @param treeId the id of the tree resource
     * @return a tree metadata
     */
    Optional<TreeMetadata> findFirstByProjectIdAndDatasetIdAndTreeId(String projectId, String datasetId, String treeId);

    /**
     * Find all tree metadata from a project id, dataset id and tree id.
     *
     * @param projectId the id of the project
     * @param datasetId the id of the dataset
     * @param treeId    the id of the tree
     * @return a list of tree metadata
     */
    List<TreeMetadata> findAllByProjectIdAndDatasetIdAndTreeId(String projectId, String datasetId, String treeId);

    /**
     * Find all metadata representations of a tree resource.
     *
     * @param treeId the id of the tree resource
     * @return a list of tree metadata
     */
    List<TreeMetadata> findAllByTreeId(String treeId);

    /**
     * Find a tree metadata from its id and repository id.
     * The repositoryId is stored as string in the document, so a custom @Query is needed.
     *
     * @param treeId       the id of the tree resource
     * @param repositoryId the id of the repository, as string, like it's stored in the document
     * @return a tree metadata
     */
    @Query("{ 'treeId' : ?0, 'repositoryId' : ?1 }")
    Optional<TreeMetadata> findByTreeIdAndRepositoryId(String treeId, String repositoryId);

    @Query("{ 'projectId' : ?0, 'datasetId' : ?1, 'treeViewId' : ?2, 'repositoryId' : ?3 }")
    Optional<TreeMetadata> findByProjectIdAndDatasetIdAndTreeViewIdAndRepositoryId(
            String projectId, String datasetId, String treeViewId, String repositoryId
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
