package org.phyloviz.pwp.administration.service.project.dataset.tree_view;

import org.phyloviz.pwp.shared.service.dtos.tree_view.TreeViewInfo;

import java.util.List;

public interface TreeViewService {

    List<TreeViewInfo> getTreeViewInfos(String datasetId);

    void deleteTreeView(String projectId, String datasetId, String treeViewId, String userId);

    void deleteAllByProjectIdAndDatasetId(String projectId, String datasetId);

    void deleteTreeView(String treeViewId);
}
