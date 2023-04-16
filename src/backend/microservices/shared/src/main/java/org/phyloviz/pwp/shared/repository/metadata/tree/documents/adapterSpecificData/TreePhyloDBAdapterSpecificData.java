package org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TreePhyloDBAdapterSpecificData implements TreeAdapterSpecificData {
    private String projectId;
    private String datasetId;
    private String inferenceId;
}
