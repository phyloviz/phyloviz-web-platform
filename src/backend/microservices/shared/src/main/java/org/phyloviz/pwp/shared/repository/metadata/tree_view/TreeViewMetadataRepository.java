package org.phyloviz.pwp.shared.repository.metadata.tree_view;

import org.phyloviz.pwp.shared.adapters.tree_view.TreeViewAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;

import java.util.List;
import java.util.Optional;

public interface TreeViewMetadataRepository {

    /**
     * Find any tree view metadata from its id.
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
     *
     * @param treeViewId the id of the tree view resource
     * @param adapterId  the id of the adapter
     * @return a tree view metadata
     */
    Optional<TreeViewMetadata> findByTreeViewIdAndAdapterId(String treeViewId, TreeViewAdapterId adapterId);

    /**
     * Find all tree view metadata from a dataset id. Only one tree view metadata per tree view resource.
     *
     * @param datasetId the id of the dataset
     * @return a list of tree view metadata
     */
    List<TreeViewMetadata> findAllByDatasetId(String datasetId);

    /**
     * Deletes a tree view metadata.
     *
     * @param treeViewMetadata the tree view metadata to delete
     */
    void delete(TreeViewMetadata treeViewMetadata);
}
