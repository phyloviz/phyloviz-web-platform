package org.phyloviz.pwp.shared.adapters.tree.adapter.specific_data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TreePhyloDBAdapterSpecificData implements TreeAdapterSpecificData {
    private String projectId;
    private String datasetId;
    private String inferenceId;
}
