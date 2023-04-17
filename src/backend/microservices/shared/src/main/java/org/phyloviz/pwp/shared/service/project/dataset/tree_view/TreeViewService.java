package org.phyloviz.pwp.shared.service.project.dataset.tree_view;

import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;
import org.phyloviz.pwp.shared.service.dtos.tree_view.TreeViewInfo;

public interface TreeViewService {

    TreeViewInfo getTreeViewInfo(String treeViewId);

    void deleteTreeView(String projectId, String datasetId, String treeViewId, String userId);

    void deleteTreeView(String treeViewId);

    GetTreeViewOutput getTreeView(String projectId, String datasetId, String treeViewId, String userId);
}
