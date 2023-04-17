package org.phyloviz.pwp.administration.http.models.trees;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.tree.TreeSourceAlgorithmTypingDataInfo;

@Data
public class TreeSourceAlgorithmTypingDataOutputModel implements TreeSourceOutputModel {
    private String algorithm;
    private String typingDataId;
    private String parameters;

    public TreeSourceAlgorithmTypingDataOutputModel(TreeSourceAlgorithmTypingDataInfo treeSourceAlgorithmTypingDataDTO) {
        this.algorithm = treeSourceAlgorithmTypingDataDTO.getAlgorithm();
        this.typingDataId = treeSourceAlgorithmTypingDataDTO.getTypingDataId();
        this.parameters = treeSourceAlgorithmTypingDataDTO.getParameters();
    }
}
