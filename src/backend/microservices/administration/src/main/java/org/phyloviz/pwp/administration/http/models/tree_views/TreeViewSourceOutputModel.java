package org.phyloviz.pwp.administration.http.models.tree_views;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.tree_view.TreeViewSourceInfo;

@Data
public class TreeViewSourceOutputModel {
    private String treeId;

    public TreeViewSourceOutputModel(TreeViewSourceInfo treeViewSourceInfo) {
        this.treeId = treeViewSourceInfo.getTreeId();
    }
}
