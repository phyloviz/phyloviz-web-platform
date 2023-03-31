package org.phyloviz.pwp.administration.http.models.trees.deleteTree;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.trees.deleteTree.DeleteTreeOutputDTO;

@Data
public class DeleteTreeOutputModel {
    private String projectId;
    private String datasetId;
    private String treeId;

    public DeleteTreeOutputModel(DeleteTreeOutputDTO deleteTreeOutputDTO) {
        this.projectId = deleteTreeOutputDTO.getProjectId();
        this.datasetId = deleteTreeOutputDTO.getProjectId();
        this.treeId = deleteTreeOutputDTO.getTreeId();
    }
}
