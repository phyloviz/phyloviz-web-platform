package org.phyloviz.pwp.administration.service.dtos.tree;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceAlgorithmDistanceMatrix;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceAlgorithmTypingData;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceFile;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceType;

@Data
public class TreeInfo {
    private final String treeId;
    private final String name;
    private final TreeSourceType sourceType;
    private final TreeSourceInfo source;

    public TreeInfo(TreeMetadata treeMetadata) {
        this.treeId = treeMetadata.getId();
        this.name = treeMetadata.getName();
        this.sourceType = treeMetadata.getSourceType();
        this.source = switch (treeMetadata.getSourceType()) {
            case ALGORITHM_DISTANCE_MATRIX -> new TreeSourceAlgorithmDistanceMatrixInfo(
                    (TreeSourceAlgorithmDistanceMatrix) treeMetadata.getSource()
            );
            case ALGORITHM_TYPING_DATA -> new TreeSourceAlgorithmTypingDataInfo(
                    (TreeSourceAlgorithmTypingData) treeMetadata.getSource()
            );

            case FILE -> new TreeSourceFileInfo(
                    (TreeSourceFile) treeMetadata.getSource()
            );
        };
    }
}
