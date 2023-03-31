package org.phyloviz.pwp.shared.repository.metadata.treeView.documents;

import lombok.Data;

@Data
public class TreeViewSource {
    private final String treeId;
    private final String typingDataId;
    private final String isolateDataId;
}
