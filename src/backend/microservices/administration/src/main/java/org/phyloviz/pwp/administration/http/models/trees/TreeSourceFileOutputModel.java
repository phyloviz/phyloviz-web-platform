package org.phyloviz.pwp.administration.http.models.trees;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.tree.TreeSourceFileInfo;

@Data
public class TreeSourceFileOutputModel implements TreeSourceOutputModel {
    private String fileType;
    private String fileName;

    public TreeSourceFileOutputModel(TreeSourceFileInfo treeSourceFileInfo) {
        this.fileType = treeSourceFileInfo.getFileType();
        this.fileName = treeSourceFileInfo.getFileName();
    }
}
