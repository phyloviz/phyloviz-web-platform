package org.phyloviz.pwp.shared.service.dtos.treeView;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.source.TreeViewSource;

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
