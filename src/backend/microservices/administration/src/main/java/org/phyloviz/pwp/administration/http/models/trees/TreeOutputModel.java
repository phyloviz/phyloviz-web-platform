package org.phyloviz.pwp.administration.http.models.trees;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.tree.TreeInfo;
import org.phyloviz.pwp.shared.service.dtos.tree.TreeSourceAlgorithmDistanceMatrixInfo;
import org.phyloviz.pwp.shared.service.dtos.tree.TreeSourceAlgorithmTypingDataInfo;
import org.phyloviz.pwp.shared.service.dtos.tree.TreeSourceFileInfo;

@Data
public class TreeOutputModel {
    private String treeId;
    private String name;
    private String sourceType;
    private TreeSourceOutputModel source;

    public TreeOutputModel(TreeInfo treeInfo) {
        this.treeId = treeInfo.getTreeId();
        this.name = treeInfo.getName();
        this.sourceType = treeInfo.getSourceType().name().toLowerCase();
        this.source = switch (treeInfo.getSourceType()) {
            case ALGORITHM_DISTANCE_MATRIX -> new TreeSourceAlgorithmDistanceMatrixOutputModel(
                    (TreeSourceAlgorithmDistanceMatrixInfo) treeInfo.getSource()
            );
            case ALGORITHM_TYPING_DATA -> new TreeSourceAlgorithmTypingDataOutputModel(
                    (TreeSourceAlgorithmTypingDataInfo) treeInfo.getSource()
            );
            case FILE -> new TreeSourceFileOutputModel(
                    (TreeSourceFileInfo) treeInfo.getSource()
            );
        };
    }
}
