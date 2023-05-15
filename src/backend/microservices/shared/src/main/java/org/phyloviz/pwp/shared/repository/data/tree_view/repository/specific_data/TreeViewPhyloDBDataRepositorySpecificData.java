package org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TreeViewPhyloDBDataRepositorySpecificData implements TreeViewDataRepositorySpecificData {
    private String projectId;
    private String datasetId;
    private String inferenceId;
    private String visualizationId;
}
