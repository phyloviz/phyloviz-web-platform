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
     * Find a tree view metadata from its id.
     *
     * @param treeViewId the id of the tree view resource
     * @return a tree view metadata
     */
    Optional<TreeViewMetadata> findByTreeViewId(String treeViewId);

    /**
     * Find all metadata representations of a tree view resource.
     *
     * @param treeViewId the id of the tree view resource
     * @return a list of tree view metadata
     */
    List<TreeViewMetadata> findAllByTreeViewId(String treeViewId);

    /**
     * Find a tree view metadata from its id and adapter id.
     * The adapterId is stored as string in the document, so a custom @Query is needed.
     *
     * @param treeViewId the id of the tree view resource
     * @param adapterId  the id of the adapter, as string, like it's stored in the document
     * @return a tree view metadata
     */
    @Query("{ 'treeViewId' : ?0, 'adapterId' : ?1 }")
    Optional<TreeViewMetadata> findByTreeViewIdAndAdapterId(String treeViewId, String adapterId);

    @Query("{ 'projectId' : ?0, 'datasetId' : ?1, 'treeViewId' : ?2, 'adapterId' : ?3 }")
    Optional<TreeViewMetadata> findByProjectIdAndDatasetIdAndTreeViewIdAndAdapterId(String projectId, String datasetId,
                                                                                    String treeViewId, String adapterId);

    List<TreeViewMetadata> findAllByProjectIdAndDatasetId(String projectId, String datasetId);

    /**
     * Find all tree view metadata from a dataset id.
     *
     * @param datasetId the id of the dataset
     * @return a list of tree view metadata
     */
    List<TreeViewMetadata> findAllByDatasetId(String datasetId);

    @Query("{ 'datasetId' : ?0, 'source' : { 'treeId' : ?1 } }")
    boolean existsByDatasetIdAndTreeIdSource(String datasetId, String treeId);

    boolean existsByProjectIdAndDatasetIdAndTreeViewId(String projectId, String datasetId, String treeViewId);
}
