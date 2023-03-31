package org.phyloviz.pwp.administration.http.models.trees;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.trees.TreeSourceAlgorithmDistanceMatrixDTO;

@Data
public class TreeSourceAlgorithmDistanceMatrixOutputModel implements TreeSourceOutputModel {
    private String algorithm;
    private String distanceMatrixId;
    private String parameters;

    public TreeSourceAlgorithmDistanceMatrixOutputModel(TreeSourceAlgorithmDistanceMatrixDTO treeSourceAlgorithmDistanceMatrixDTO) {
        this.algorithm = treeSourceAlgorithmDistanceMatrixDTO.getAlgorithm();
        this.distanceMatrixId = treeSourceAlgorithmDistanceMatrixDTO.getDistanceMatrixId();
        this.parameters = treeSourceAlgorithmDistanceMatrixDTO.getParameters();
    }
}
