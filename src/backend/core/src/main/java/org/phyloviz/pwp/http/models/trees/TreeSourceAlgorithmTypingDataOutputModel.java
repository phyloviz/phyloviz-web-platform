package org.phyloviz.pwp.http.models.trees;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.tree.TreeSourceAlgorithmTypingDataInfo;

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
