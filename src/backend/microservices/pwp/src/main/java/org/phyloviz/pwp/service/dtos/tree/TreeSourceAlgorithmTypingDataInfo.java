package org.phyloviz.pwp.service.dtos.tree;

import lombok.Data;
import org.phyloviz.pwp.repository.metadata.tree.documents.source.TreeSourceAlgorithmTypingData;

@Data
public class TreeSourceAlgorithmTypingDataInfo implements TreeSourceInfo {
    private final String algorithm;
    private final String typingDataId;
    private final String parameters;

    public TreeSourceAlgorithmTypingDataInfo(TreeSourceAlgorithmTypingData treeSourceAlgorithmTypingData) {
        this.algorithm = treeSourceAlgorithmTypingData.getAlgorithm();
        this.typingDataId = treeSourceAlgorithmTypingData.getTypingDataId();
        this.parameters = treeSourceAlgorithmTypingData.getParameters();
    }
}
