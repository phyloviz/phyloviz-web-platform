package org.phyloviz.pwp.http.models.tree_views.delete_tree_view;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteTreeViewOutputModel {
    private String projectId;
    private String datasetId;
    private String treeViewId;
}
