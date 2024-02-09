package org.phyloviz.pwp.http.models.trees;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.tree.TreeSourceFileInfo;

@Data
public class TreeSourceFileOutputModel implements TreeSourceOutputModel {
    private String fileType;
    private String fileName;

    public TreeSourceFileOutputModel(TreeSourceFileInfo treeSourceFileInfo) {
        this.fileType = treeSourceFileInfo.getFileType();
        this.fileName = treeSourceFileInfo.getFileName();
    }
}
