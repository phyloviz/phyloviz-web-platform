package org.phyloviz.pwp.service.dtos.tree_view;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.source.TreeViewSource;

@Data
public class TreeViewSourceInfo {
    private final String treeId;

    public TreeViewSourceInfo(TreeViewSource treeViewSource) {
        this.treeId = treeViewSource.getTreeId();
    }
}
