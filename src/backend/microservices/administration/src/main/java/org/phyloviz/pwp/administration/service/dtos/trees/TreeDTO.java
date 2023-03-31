package org.phyloviz.pwp.administration.service.dtos.trees;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeSourceAlgorithmDistanceMatrix;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeSourceAlgorithmTypingData;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeSourceFile;

@Data
public class TreeDTO {
    private final String treeId;
    private final String name;
    private final String sourceType;
    private final TreeSourceDTO source;

    public TreeDTO(TreeMetadata treeMetadata) {
        this.treeId = treeMetadata.getId();
        this.name = treeMetadata.getName();
        this.sourceType = treeMetadata.getSourceType();
        this.source = switch (treeMetadata.getSourceType()) {
            case "algorithmDistanceMatrix" -> new TreeSourceAlgorithmDistanceMatrixDTO(
                    (TreeSourceAlgorithmDistanceMatrix) treeMetadata.getSource()
            );
            case "algorithmTypingData" -> new TreeSourceAlgorithmTypingDataDTO(
                    (TreeSourceAlgorithmTypingData) treeMetadata.getSource()
            );

            case "file" -> new TreeSourceFileDTO(
                    (TreeSourceFile) treeMetadata.getSource()
            );
            default -> null;
        };
    }
}
