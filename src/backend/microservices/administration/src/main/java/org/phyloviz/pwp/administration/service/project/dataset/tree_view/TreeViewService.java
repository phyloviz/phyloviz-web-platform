package org.phyloviz.pwp.administration.service.project.dataset.tree_view;

import org.phyloviz.pwp.administration.service.dtos.tree_view.TreeViewInfo;
import org.phyloviz.pwp.administration.service.dtos.tree_view.UpdateTreeViewOutput;

import java.util.List;

public interface TreeViewService {

    List<TreeViewInfo> getTreeViewInfos(String datasetId);

    void deleteTreeView(String projectId, String datasetId, String treeViewId, String userId);

    void deleteAllByProjectIdAndDatasetId(String projectId, String datasetId);

    void deleteTreeView(String treeViewId);

    UpdateTreeViewOutput updateTreeView(String name, String projectId, String datasetId, String treeViewId, String userId);
}
