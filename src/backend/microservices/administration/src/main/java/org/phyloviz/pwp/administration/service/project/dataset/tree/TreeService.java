package org.phyloviz.pwp.administration.service.project.dataset.tree;

import org.phyloviz.pwp.shared.service.dtos.tree.TreeInfo;

import java.util.List;

public interface TreeService {

    List<TreeInfo> getTreeInfos(String datasetId);

    void deleteTree(String projectId, String datasetId, String treeId, String userId);

    void deleteAllByProjectIdAndDatasetId(String projectId, String datasetId);

    void deleteTree(String treeId);
}
