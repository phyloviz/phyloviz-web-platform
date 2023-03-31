package org.phyloviz.pwp.shared.repository.metadata.tree.documents;

import lombok.Data;

@Data
public class TreeSourceAlgorithmDistanceMatrix implements TreeSource {
    private final String algorithm;
    private final String distanceMatrixId;
    private final String parameters;
}
