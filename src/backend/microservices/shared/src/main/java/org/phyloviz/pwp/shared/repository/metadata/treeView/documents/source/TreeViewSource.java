package org.phyloviz.pwp.shared.repository.metadata.treeView.documents.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeViewSource {
    private String treeId;
    private String typingDataId;
    private String isolateDataId;
}
