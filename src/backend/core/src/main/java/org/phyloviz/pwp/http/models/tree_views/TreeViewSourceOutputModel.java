package org.phyloviz.pwp.http.models.tree_views;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.tree_view.TreeViewSourceInfo;

@Data
public class TreeViewSourceOutputModel {
    private String treeId;

    public TreeViewSourceOutputModel(TreeViewSourceInfo treeViewSourceInfo) {
        this.treeId = treeViewSourceInfo.getTreeId();
    }
}
