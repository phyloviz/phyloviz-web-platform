package org.phyloviz.pwp.shared_phylodb.adapters.tree.phylodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.phyloviz.pwp.shared.adapters.tree.adapter.specific_data.TreeAdapterSpecificData;

@Data
@AllArgsConstructor
public class TreePhyloDBAdapterSpecificData implements TreeAdapterSpecificData {
    private String projectId;
    private String datasetId;
    private String inferenceId;
}
