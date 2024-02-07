package org.phyloviz.pwp.service.dtos.tree_view;

import lombok.Data;
import org.phyloviz.pwp.repository.metadata.tree_view.documents.TreeViewMetadata;

@Data
public class TreeViewInfo {
    private final String treeViewId;
    private final String name;
    private final String layout;
    private final TreeViewSourceInfo source;

    public TreeViewInfo(TreeViewMetadata treeViewMetadata) {
        this.treeViewId = treeViewMetadata.getTreeViewId();
        this.name = treeViewMetadata.getName();
        this.layout = treeViewMetadata.getLayout();
        this.source = new TreeViewSourceInfo(treeViewMetadata.getSource());
    }
}