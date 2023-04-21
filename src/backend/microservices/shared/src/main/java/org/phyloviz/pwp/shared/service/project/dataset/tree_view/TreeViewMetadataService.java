package org.phyloviz.pwp.shared.service.project.dataset.tree_view;

import org.phyloviz.pwp.shared.adapters.tree_view.TreeViewAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;

import java.util.List;

public interface TreeViewMetadataService {

    TreeViewMetadata getTreeViewMetadata(String projectId, String datasetId, String treeViewId, String userId);

    TreeViewMetadata getTreeViewMetadata(String treeViewId);

    TreeViewMetadata getTreeViewMetadataOrNull(String treeViewId);

    TreeViewMetadata getTreeViewMetadataByAdapterIdOrNull(String treeViewId, TreeViewAdapterId adapterId);

    List<TreeViewMetadata> getAllTreeViewMetadata(String treeViewId);

    /**
     * Find all tree view metadata from a dataset id. Only one tree view metadata per tree view resource.
     *
     * @param datasetId the id of the dataset
     * @return a list of tree view metadata
     */
    List<TreeViewMetadata> getAllTreeViewMetadataByDatasetId(String datasetId);

    void deleteTreeView(TreeViewMetadata treeViewMetadata);

    void assertExists(String projectId, String datasetId, String treeViewId, String userId);
}
