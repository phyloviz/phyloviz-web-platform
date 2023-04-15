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
        this.sourceType = treeMetadataDTO.getSourceType();
        this.source = switch (treeMetadataDTO.getSourceType()) {
            case "algorithmDistanceMatrix" -> new TreeSourceAlgorithmDistanceMatrixOutputModel(
                    (TreeSourceAlgorithmDistanceMatrixDTO) treeMetadataDTO.getSource()
            );
            case "algorithmTypingData" -> new TreeSourceAlgorithmTypingDataOutputModel(
                    (TreeSourceAlgorithmTypingDataDTO) treeMetadataDTO.getSource()
            );
            case "file" -> new TreeSourceFileOutputModel(
                    (TreeSourceFileDTO) treeMetadataDTO.getSource()
            );
            default ->
                    throw new IllegalArgumentException("Unknown tree source type: " + treeMetadataDTO.getSourceType());
        };
    }
}
