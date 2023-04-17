package org.phyloviz.pwp.shared.service.project.dataset.tree_view;

import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;

public interface TreeViewAccessService {

    TreeViewMetadata getTreeViewMetadata(String projectId, String datasetId, String treeViewId, String userId);

    TreeViewMetadata getTreeViewMetadata(String treeViewId);

    TreeViewMetadata getTreeViewMetadataOrNull(String treeViewId);

    void deleteTreeView(String treeViewId);

    void assertExists(String projectId, String datasetId, String treeViewId, String userId);

    GetTreeViewOutput getTreeView(String projectId, String datasetId, String treeViewId, String userId);
}
