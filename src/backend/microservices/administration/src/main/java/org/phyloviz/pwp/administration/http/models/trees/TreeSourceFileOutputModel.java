package org.phyloviz.pwp.administration.http.models.trees;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.trees.TreeSourceFileDTO;

@Data
public class TreeSourceFileOutputModel implements TreeSourceOutputModel {
    private String fileType;
    private String fileName;

    public TreeSourceFileOutputModel(TreeSourceFileDTO treeSourceFileDTO) {
        this.fileType = treeSourceFileDTO.getFileType();
        this.fileName = treeSourceFileDTO.getFileName();
    }
}
