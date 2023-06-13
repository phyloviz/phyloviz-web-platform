package org.phyloviz.pwp.shared.repository.metadata.tree_view.mongo;

import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TreeViewMetadataMongoRepository extends MongoRepository<TreeViewMetadata, String> {

    /**
     * Find the first tree view metadata with the given id. Analogous to findAny().
     *
     * @param treeViewId the id of the tree view resource
     * @return a tree view metadata
     */
    Optional<TreeViewMetadata> findByProjectIdAndDatasetIdAndTreeViewId(String projectId, String datasetId, String treeViewId);

    /**
     * Find all tree view metadata from a project id and dataset id.
     *
     * @param projectId the id of the project
     * @param datasetId the id of the dataset
     * @return a list of tree view metadata
     */
    List<TreeViewMetadata> findAllByProjectIdAndDatasetId(String projectId, String datasetId);

    /**
     * Find all tree view metadata from a dataset id.
     *
     * @param datasetId the id of the dataset
     * @return a list of tree view metadata
     */
    List<TreeViewMetadata> findAllByDatasetId(String datasetId);

    /**
     * Find a tree view metadata with dataset id and its source is a tree of tree id.
     *
     * @param datasetId the id of the dataset
     * @param treeId    the id of the tree
     * @return the tree view metadata
     */
    @Query("{ 'datasetId' : ?0, 'source.treeId' : ?1 }")
    List<TreeViewMetadata> findByDatasetIdAndTreeIdSource(String datasetId, String treeId);
}
