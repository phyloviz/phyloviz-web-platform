package org.phyloviz.pwp.administration.http.models.trees;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.tree.TreeSourceAlgorithmTypingDataInfo;

@Data
public class TreeSourceAlgorithmTypingDataOutputModel implements TreeSourceOutputModel {
    private String algorithm;
    private String typingDataId;
    private String parameters;

    public TreeSourceAlgorithmTypingDataOutputModel(TreeSourceAlgorithmTypingDataInfo treeSourceAlgorithmTypingDataInfo) {
        this.algorithm = treeSourceAlgorithmTypingDataInfo.getAlgorithm();
        this.typingDataId = treeSourceAlgorithmTypingDataInfo.getTypingDataId();
        this.parameters = treeSourceAlgorithmTypingDataInfo.getParameters();
    }
}
