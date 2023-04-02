package org.phyloviz.pwp.shared.repository.metadata.tree.documents.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("treeSourceAlgorithmDistanceMatrix")
public class TreeSourceAlgorithmDistanceMatrix implements TreeSource {
    private String algorithm;
    private String distanceMatrixId;
    private String parameters;
}
