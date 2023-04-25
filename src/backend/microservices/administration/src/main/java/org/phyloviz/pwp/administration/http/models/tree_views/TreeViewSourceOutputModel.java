package org.phyloviz.pwp.administration.http.models.tree_views;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.tree_view.TreeViewSourceInfo;

@Data
public class TreeViewSourceOutputModel {
    private String treeId;
    private String typingDataId;
    private String isolateDataId;

    public TreeViewSourceOutputModel(TreeViewSourceInfo treeViewSourceInfo) {
        this.treeId = treeViewSourceInfo.getTreeId();
        this.typingDataId = treeViewSourceInfo.getTypingDataId();
        this.isolateDataId = treeViewSourceInfo.getIsolateDataId();
    }
}
