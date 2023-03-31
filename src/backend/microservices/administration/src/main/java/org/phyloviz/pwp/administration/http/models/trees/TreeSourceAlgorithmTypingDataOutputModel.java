package org.phyloviz.pwp.administration.http.models.trees;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.trees.TreeSourceAlgorithmTypingDataDTO;

@Data
public class TreeSourceAlgorithmTypingDataOutputModel implements TreeSourceOutputModel {
    private String algorithm;
    private String typingDataId;
    private String parameters;

    public TreeSourceAlgorithmTypingDataOutputModel(TreeSourceAlgorithmTypingDataDTO treeSourceAlgorithmTypingDataDTO) {
        this.algorithm = treeSourceAlgorithmTypingDataDTO.getAlgorithm();
        this.typingDataId = treeSourceAlgorithmTypingDataDTO.getTypingDataId();
        this.parameters = treeSourceAlgorithmTypingDataDTO.getParameters();
    }
}
