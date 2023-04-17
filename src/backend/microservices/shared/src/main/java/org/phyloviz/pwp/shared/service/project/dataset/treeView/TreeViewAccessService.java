package org.phyloviz.pwp.shared.service.project.dataset.treeView;

import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.service.dtos.treeView.GetTreeViewOutput;

public interface TreeViewAccessService {

    TreeViewMetadata getTreeViewMetadata(String projectId, String datasetId, String treeViewId, String userId);

    TreeViewMetadata getTreeViewMetadata(String treeViewId);

    TreeViewMetadata getTreeViewMetadataOrNull(String treeViewId);

    void deleteTreeView(String treeViewId);

    void assertExists(String projectId, String datasetId, String treeViewId, String userId);

    GetTreeViewOutput getTreeView(String projectId, String datasetId, String treeViewId, String userId);
}
