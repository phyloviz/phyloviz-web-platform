package org.phyloviz.pwp.shared.service.project.dataset.treeView;

import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.service.dtos.treeView.GetTreeViewOutput;
import org.phyloviz.pwp.shared.service.dtos.treeView.TreeViewMetadataDTO;

public interface TreeViewService {

    TreeViewMetadata getTreeViewMetadata(String projectId, String datasetId, String treeViewId, String userId);

    TreeViewMetadata getTreeViewMetadata(String treeViewId);

    TreeViewMetadata getTreeViewMetadataOrNull(String treeViewId);

    TreeViewMetadataDTO getTreeViewMetadataDTO(String treeViewId);

    void assertExists(String projectId, String datasetId, String treeViewId, String userId);

    void deleteTreeView(String projectId, String datasetId, String treeViewId, String userId);

    void deleteTreeView(String treeViewId);

    GetTreeViewOutput getTreeView(String projectId, String datasetId, String treeViewId, String userId);
}
