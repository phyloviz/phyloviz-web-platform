package org.phyloviz.pwp.administration.http.models.treeViews;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.treeViews.TreeViewSourceDTO;

@Data
public class TreeViewSourceOutputModel {
    private String treeId;
    private String typingDataId;
    private String isolateDataId;

    public TreeViewSourceOutputModel(TreeViewSourceDTO treeViewSourceDTO) {
        this.treeId = treeViewSourceDTO.getTreeId();
        this.typingDataId = treeViewSourceDTO.getTypingDataId();
        this.isolateDataId = treeViewSourceDTO.getIsolateDataId();
    }
}
