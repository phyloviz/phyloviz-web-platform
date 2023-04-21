package org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeViewSource {
    private String treeId;
    private String typingDataId;
    private String isolateDataId;
}
