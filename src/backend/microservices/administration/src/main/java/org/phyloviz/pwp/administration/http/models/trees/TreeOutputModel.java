package org.phyloviz.pwp.administration.http.models.trees;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.tree.TreeMetadataDTO;
import org.phyloviz.pwp.shared.service.dtos.tree.TreeSourceAlgorithmDistanceMatrixDTO;
import org.phyloviz.pwp.shared.service.dtos.tree.TreeSourceAlgorithmTypingDataDTO;
import org.phyloviz.pwp.shared.service.dtos.tree.TreeSourceFileDTO;

@Data
public class TreeOutputModel {
    private String treeId;
    private String name;
    private String sourceType;
    private TreeSourceOutputModel source;

    public TreeOutputModel(TreeMetadataDTO treeMetadataDTO) {
        this.treeId = treeMetadataDTO.getTreeId();
        this.name = treeMetadataDTO.getName();
        this.sourceType = treeMetadataDTO.getSourceType().name().toLowerCase();
        this.source = switch (treeMetadataDTO.getSourceType()) {
            case ALGORITHM_DISTANCE_MATRIX -> new TreeSourceAlgorithmDistanceMatrixOutputModel(
                    (TreeSourceAlgorithmDistanceMatrixDTO) treeMetadataDTO.getSource()
            );
            case ALGORITHM_TYPING_DATA -> new TreeSourceAlgorithmTypingDataOutputModel(
                    (TreeSourceAlgorithmTypingDataDTO) treeMetadataDTO.getSource()
            );
            case FILE -> new TreeSourceFileOutputModel(
                    (TreeSourceFileDTO) treeMetadataDTO.getSource()
            );
        };
    }
}
