package org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

@Data
@AllArgsConstructor
@TypeAlias("treeS3AdapterSpecificData")
public class TreeS3AdapterSpecificData implements TreeAdapterSpecificData {
}
