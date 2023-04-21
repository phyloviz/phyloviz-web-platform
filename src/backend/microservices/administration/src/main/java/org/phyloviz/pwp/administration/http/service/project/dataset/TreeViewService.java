package org.phyloviz.pwp.administration.http.service.project.dataset;

import org.phyloviz.pwp.shared.service.dtos.tree_view.TreeViewInfo;

import java.util.List;

public interface TreeViewService {

    List<TreeViewInfo> getTreeViewInfos(String datasetId);

    void deleteTreeView(String projectId, String datasetId, String treeViewId, String userId);

    void deleteTreeView(String treeViewId);
}
