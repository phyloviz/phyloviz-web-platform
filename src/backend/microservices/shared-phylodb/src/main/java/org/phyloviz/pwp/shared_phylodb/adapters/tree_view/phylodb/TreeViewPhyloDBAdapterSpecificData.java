package org.phyloviz.pwp.shared_phylodb.adapters.tree_view.phylodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.specific_data.TreeViewAdapterSpecificData;

@Data
@AllArgsConstructor
public class TreeViewPhyloDBAdapterSpecificData implements TreeViewAdapterSpecificData {
    private String projectId;
    private String datasetId;
    private String inferenceId;
    private String visualizationId;
}
