package org.phyloviz.pwp.http.models.trees;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.tree.TreeSourceAlgorithmDistanceMatrixInfo;

@Data
public class TreeSourceAlgorithmDistanceMatrixOutputModel implements TreeSourceOutputModel {
    private String algorithm;
    private String distanceMatrixId;
    private String parameters;

    public TreeSourceAlgorithmDistanceMatrixOutputModel(TreeSourceAlgorithmDistanceMatrixInfo treeSourceAlgorithmDistanceMatrixInfo) {
        this.algorithm = treeSourceAlgorithmDistanceMatrixInfo.getAlgorithm();
        this.distanceMatrixId = treeSourceAlgorithmDistanceMatrixInfo.getDistanceMatrixId();
        this.parameters = treeSourceAlgorithmDistanceMatrixInfo.getParameters();
    }
}
