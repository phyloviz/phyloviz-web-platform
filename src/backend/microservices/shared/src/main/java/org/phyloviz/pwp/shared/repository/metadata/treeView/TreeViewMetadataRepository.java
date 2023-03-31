package org.phyloviz.pwp.shared.repository.metadata.treeView;

import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewMetadata;

import java.util.List;

public interface TreeViewMetadataRepository {

    /**
     * Deletes a tree view metadata.
     *
     * @param treeViewMetadata the tree view metadata to delete
     */
    void deleteTreeView(TreeViewMetadata treeViewMetadata);

    /**
     * Find a tree view metadata from its id.
     *
     * @param treeViewId the id of the tree view resource
     * @return a tree view metadata
     */
    TreeViewMetadata findByTreeViewId(String treeViewId);

    /**
     * Find all metadata representations of a tree view resource.
     *
     * @param treeViewId the id of the tree view resource
     * @return a list of tree view metadata
     */
    List<TreeViewMetadata> findAllByTreeViewId(String treeViewId);
}
