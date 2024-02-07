package org.phyloviz.pwp.http.models.save_tree_view;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.tree_view.SaveTreeViewOutput;

/**
 * Output model for the save tree view endpoint.
 */
@Data
public class SaveTreeViewOutputModel {
    private String projectId;
    private String datasetId;
    private String treeViewId;

    public SaveTreeViewOutputModel(SaveTreeViewOutput saveTreeViewOutput) {
        this.projectId = saveTreeViewOutput.getProjectId();
        this.datasetId = saveTreeViewOutput.getDatasetId();
        this.treeViewId = saveTreeViewOutput.getTreeViewId();
    }
}
