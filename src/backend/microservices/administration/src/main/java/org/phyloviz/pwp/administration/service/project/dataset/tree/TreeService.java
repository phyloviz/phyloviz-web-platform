package org.phyloviz.pwp.administration.service.project.dataset.tree;

import org.phyloviz.pwp.administration.service.dtos.tree.TreeInfo;
import org.phyloviz.pwp.administration.service.dtos.tree.UpdateTreeOutput;

import java.util.List;

public interface TreeService {

    List<TreeInfo> getTreeInfos(String datasetId);

    void deleteTree(String projectId, String datasetId, String treeId, String userId);

    void deleteAllByProjectIdAndDatasetId(String projectId, String datasetId);

    UpdateTreeOutput updateTree(String name, String projectId, String datasetId, String treeId, String userId);
}
