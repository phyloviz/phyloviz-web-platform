package org.phyloviz.pwp.shared.repository.metadata.tree_view;

import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;

public interface TreeViewMetadataRepository {

    /**
     * Deletes a tree view.
     *
     * @param treeViewMetadata the tree view to delete
     */
    void deleteTreeView(TreeViewMetadata treeViewMetadata);

    /**
     * Find a tree view metadata from its resource id.
     *
     * @param resourceId the resource id of the tree view
     * @return a tree view metadata
     */
    TreeViewMetadata findByResourceId(String resourceId);
}
