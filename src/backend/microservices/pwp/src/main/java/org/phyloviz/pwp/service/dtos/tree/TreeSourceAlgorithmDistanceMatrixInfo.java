package org.phyloviz.pwp.service.dtos.tree;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceAlgorithmDistanceMatrix;

@Data
public class TreeSourceAlgorithmDistanceMatrixInfo implements TreeSourceInfo {
    private final String algorithm;
    private final String distanceMatrixId;
    private final String parameters;

    public TreeSourceAlgorithmDistanceMatrixInfo(TreeSourceAlgorithmDistanceMatrix treeSourceAlgorithmDistanceMatrix) {
        this.algorithm = treeSourceAlgorithmDistanceMatrix.getAlgorithm();
        this.distanceMatrixId = treeSourceAlgorithmDistanceMatrix.getDistanceMatrixId();
        this.parameters = treeSourceAlgorithmDistanceMatrix.getParameters();
    }
}
