package org.phyloviz.pwp.administration.http.models.treeViews.deleteTreeView;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteTreeViewOutputModel {
    private String projectId;
    private String datasetId;
    private String treeViewId;
}
