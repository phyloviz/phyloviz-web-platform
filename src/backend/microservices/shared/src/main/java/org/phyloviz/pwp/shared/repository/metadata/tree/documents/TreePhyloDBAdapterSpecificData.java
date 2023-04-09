package org.phyloviz.pwp.shared.repository.metadata.tree.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

@Data
@AllArgsConstructor
@TypeAlias("treePhyloDBAdapterSpecificData")
public class TreePhyloDBAdapterSpecificData {
    private String inferenceId;
}
