package org.phyloviz.pwp.administration.http.service.project.dataset;

import org.phyloviz.pwp.shared.service.dtos.tree.TreeInfo;

import java.util.List;

public interface TreeService {

    List<TreeInfo> getTreeInfos(String datasetId);

    void deleteTree(String projectId, String datasetId, String treeId, String userId);

    void deleteTree(String treeId);
}
