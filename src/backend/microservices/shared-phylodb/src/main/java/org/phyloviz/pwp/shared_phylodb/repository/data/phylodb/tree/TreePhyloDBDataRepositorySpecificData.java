package org.phyloviz.pwp.shared_phylodb.repository.data.phylodb.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.phyloviz.pwp.shared.repository.data.tree.repository.specific_data.TreeDataRepositorySpecificData;

@Data
@AllArgsConstructor
public class TreePhyloDBDataRepositorySpecificData implements TreeDataRepositorySpecificData {
    private String projectId;
    private String datasetId;
    private String inferenceId;
}
