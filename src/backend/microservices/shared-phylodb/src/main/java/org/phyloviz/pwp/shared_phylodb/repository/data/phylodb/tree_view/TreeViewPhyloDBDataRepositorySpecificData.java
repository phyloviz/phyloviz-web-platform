package org.phyloviz.pwp.shared_phylodb.repository.data.phylodb.tree_view;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewDataRepositorySpecificData;

@Data
@AllArgsConstructor
public class TreeViewPhyloDBDataRepositorySpecificData implements TreeViewDataRepositorySpecificData {
    private String projectId;
    private String datasetId;
    private String inferenceId;
    private String visualizationId;
}
