package org.phyloviz.pwp.shared.service.dtos.tree;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceAlgorithmDistanceMatrix;

@Data
public class TreeSourceAlgorithmDistanceMatrixDTO implements TreeSourceDTO {
    private final String algorithm;
    private final String distanceMatrixId;
    private final String parameters;

    public TreeSourceAlgorithmDistanceMatrixDTO(TreeSourceAlgorithmDistanceMatrix treeSourceAlgorithmDistanceMatrix) {
        this.algorithm = treeSourceAlgorithmDistanceMatrix.getAlgorithm();
        this.distanceMatrixId = treeSourceAlgorithmDistanceMatrix.getDistanceMatrixId();
        this.parameters = treeSourceAlgorithmDistanceMatrix.getParameters();
    }
}
