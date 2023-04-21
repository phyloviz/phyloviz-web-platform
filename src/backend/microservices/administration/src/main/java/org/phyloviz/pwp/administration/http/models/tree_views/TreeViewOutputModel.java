package org.phyloviz.pwp.administration.http.models.tree_views;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.tree_view.TreeViewInfo;

@Data
public class TreeViewOutputModel {
    private String treeViewId;
    private String name;
    private String layout;
    private TreeViewSourceOutputModel source;

    public TreeViewOutputModel(TreeViewInfo treeViewInfo) {
        this.treeViewId = treeViewInfo.getTreeViewId();
        this.name = treeViewInfo.getName();
        this.layout = treeViewInfo.getLayout();
        this.source = new TreeViewSourceOutputModel(treeViewInfo.getSource());
    }
}
