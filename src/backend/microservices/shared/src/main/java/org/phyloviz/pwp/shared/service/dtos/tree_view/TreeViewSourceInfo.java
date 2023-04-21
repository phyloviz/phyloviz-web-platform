package org.phyloviz.pwp.shared.service.dtos.tree_view;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.source.TreeViewSource;

@Data
public class TreeViewSourceInfo {
    private final String treeId;
    private final String typingDataId;
    private final String isolateDataId;

    public TreeViewSourceInfo(TreeViewSource treeViewSource) {
        this.treeId = treeViewSource.getTreeId();
        this.typingDataId = treeViewSource.getTypingDataId();
        this.isolateDataId = treeViewSource.getIsolateDataId();
    }
}
