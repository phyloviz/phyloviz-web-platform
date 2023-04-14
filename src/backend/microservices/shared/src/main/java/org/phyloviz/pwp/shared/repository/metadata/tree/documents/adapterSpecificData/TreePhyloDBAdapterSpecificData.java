package org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

@Data
@AllArgsConstructor
@TypeAlias("treePhyloDBAdapterSpecificData")
public class TreePhyloDBAdapterSpecificData implements TreeAdapterSpecificData {
    private String projectId;
    private String datasetId;
    private String inferenceId;
}
