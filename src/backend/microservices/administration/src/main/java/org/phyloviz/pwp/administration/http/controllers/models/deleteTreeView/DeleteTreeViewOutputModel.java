package org.phyloviz.pwp.administration.http.controllers.models.deleteTreeView;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.deleteTreeView.DeleteTreeViewOutputDTO;

@Data
public class DeleteTreeViewOutputModel {
    private String projectId;
    private String treeViewId;

    public DeleteTreeViewOutputModel(DeleteTreeViewOutputDTO deleteTreeViewOutputDTO) {
        this.projectId = deleteTreeViewOutputDTO.getProjectId();
        this.treeViewId = deleteTreeViewOutputDTO.getTreeViewId();
    }
}
