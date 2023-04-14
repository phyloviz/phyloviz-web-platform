package org.phyloviz.pwp.administration.http.models.treeViews.deleteTreeView;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.treeViews.deleteTreeView.DeleteTreeViewOutputDTO;

@Data
public class DeleteTreeViewOutputModel {
    private String projectId;
    private String datasetId;
    private String treeViewId;

    public DeleteTreeViewOutputModel(DeleteTreeViewOutputDTO deleteTreeViewOutputDTO) {
        this.projectId = deleteTreeViewOutputDTO.getProjectId();
        this.datasetId = deleteTreeViewOutputDTO.getDatasetId();
        this.treeViewId = deleteTreeViewOutputDTO.getTreeViewId();
    }
}
