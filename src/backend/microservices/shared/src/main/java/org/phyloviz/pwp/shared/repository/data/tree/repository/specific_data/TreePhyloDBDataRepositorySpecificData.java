package org.phyloviz.pwp.shared.repository.data.tree.repository.specific_data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TreePhyloDBDataRepositorySpecificData implements TreeDataRepositorySpecificData {
    private String projectId;
    private String datasetId;
    private String inferenceId;
}
