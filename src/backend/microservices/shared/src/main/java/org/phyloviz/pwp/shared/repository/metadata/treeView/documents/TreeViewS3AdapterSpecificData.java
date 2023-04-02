package org.phyloviz.pwp.shared.repository.metadata.treeView.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

@Data
@AllArgsConstructor
@TypeAlias("treeViewS3AdapterSpecificData")
public class TreeViewS3AdapterSpecificData implements TreeViewAdapterSpecificData {
}
