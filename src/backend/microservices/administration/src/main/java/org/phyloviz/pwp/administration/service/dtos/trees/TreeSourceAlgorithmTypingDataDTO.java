package org.phyloviz.pwp.administration.service.dtos.trees;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeSourceAlgorithmTypingData;

@Data
public class TreeSourceAlgorithmTypingDataDTO implements TreeSourceDTO {
    private final String algorithm;
    private final String typingDataId;
    private final String parameters;

    public TreeSourceAlgorithmTypingDataDTO(TreeSourceAlgorithmTypingData treeSourceAlgorithmTypingData) {
        this.algorithm = treeSourceAlgorithmTypingData.getAlgorithm();
        this.typingDataId = treeSourceAlgorithmTypingData.getTypingDataId();
        this.parameters = treeSourceAlgorithmTypingData.getParameters();
    }
}
