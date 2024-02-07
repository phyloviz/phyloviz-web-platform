package org.phyloviz.pwp.repository.metadata.tree.documents.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeSourceAlgorithmDistanceMatrix implements TreeSource {
    private String algorithm;
    private String distanceMatrixId;
    private String parameters;
}
