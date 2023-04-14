package org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

@Data
@AllArgsConstructor
@TypeAlias("treeViewPhyloDBAdapterSpecificData")
public class TreeViewPhyloDBAdapterSpecificData implements TreeViewAdapterSpecificData {
    private String projectId;
    private String datasetId;
    private String inferenceId;
    private String visualizationId;
}
