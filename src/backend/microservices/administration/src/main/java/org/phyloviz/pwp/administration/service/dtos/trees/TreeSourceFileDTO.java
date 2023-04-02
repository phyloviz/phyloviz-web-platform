package org.phyloviz.pwp.administration.service.dtos.trees;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceFile;

@Data
public class TreeSourceFileDTO implements TreeSourceDTO {
    private final String fileType;
    private final String fileName;

    public TreeSourceFileDTO(TreeSourceFile treeSourceFile) {
        this.fileType = treeSourceFile.getFileType();
        this.fileName = treeSourceFile.getFileName();
    }
}
