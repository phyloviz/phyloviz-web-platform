package org.phyloviz.pwp.shared.service.project.dataset.tree;

import org.phyloviz.pwp.shared.service.dtos.tree.TreeInfo;

public interface TreeService {

    TreeInfo getTreeInfo(String treeId);

    void deleteTree(String projectId, String datasetId, String treeId, String userId);

    void deleteTree(String treeId);

    String getTree(String projectId, String datasetId, String treeId, String userId);
}
