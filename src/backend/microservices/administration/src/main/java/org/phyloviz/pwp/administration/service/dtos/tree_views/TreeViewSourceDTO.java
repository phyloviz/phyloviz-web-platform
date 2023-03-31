package org.phyloviz.pwp.administration.service.dtos.tree_views;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewSource;

@Data
public class TreeViewSourceDTO {
    private final String treeId;
    private final String typingDataId;
    private final String isolateDataId;

    public TreeViewSourceDTO(TreeViewSource treeViewSource) {
        this.treeId = treeViewSource.getTreeId();
        this.typingDataId = treeViewSource.getTypingDataId();
        this.isolateDataId = treeViewSource.getIsolateDataId();
    }
}
