package org.phyloviz.pwp.administration.http.models.trees;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.trees.TreeDTO;
import org.phyloviz.pwp.administration.service.dtos.trees.TreeSourceAlgorithmDistanceMatrixDTO;
import org.phyloviz.pwp.administration.service.dtos.trees.TreeSourceAlgorithmTypingDataDTO;
import org.phyloviz.pwp.administration.service.dtos.trees.TreeSourceFileDTO;

@Data
public class TreeOutputModel {
    private String treeId;
    private String name;
    private String sourceType;
    private TreeSourceOutputModel source;

    public TreeOutputModel(TreeDTO treeDTO) {
        this.treeId = treeDTO.getTreeId();
        this.name = treeDTO.getName();
        this.sourceType = treeDTO.getSourceType();
        this.source = switch (treeDTO.getSourceType()) {
            case "algorithmDistanceMatrix" -> new TreeSourceAlgorithmDistanceMatrixOutputModel(
                    (TreeSourceAlgorithmDistanceMatrixDTO) treeDTO.getSource()
            );
            case "algorithmTypingData" -> new TreeSourceAlgorithmTypingDataOutputModel(
                    (TreeSourceAlgorithmTypingDataDTO) treeDTO.getSource()
            );
            case "file" -> new TreeSourceFileOutputModel(
                    (TreeSourceFileDTO) treeDTO.getSource()
            );
            default -> throw new IllegalArgumentException("Unknown tree source type: " + treeDTO.getSourceType());
        };
    }
}
