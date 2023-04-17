package org.phyloviz.pwp.shared.adapters.tree_view.adapter.specific_data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TreeViewPhyloDBAdapterSpecificData implements TreeViewAdapterSpecificData {
    private String projectId;
    private String datasetId;
    private String inferenceId;
    private String visualizationId;
}
