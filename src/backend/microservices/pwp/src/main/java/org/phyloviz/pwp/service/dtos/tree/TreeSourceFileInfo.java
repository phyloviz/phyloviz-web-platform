package org.phyloviz.pwp.service.dtos.tree;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceFile;

@Data
public class TreeSourceFileInfo implements TreeSourceInfo {
    private final String fileType;
    private final String fileName;

    public TreeSourceFileInfo(TreeSourceFile treeSourceFile) {
        this.fileType = treeSourceFile.getFileType();
        this.fileName = treeSourceFile.getFileName();
    }
}
