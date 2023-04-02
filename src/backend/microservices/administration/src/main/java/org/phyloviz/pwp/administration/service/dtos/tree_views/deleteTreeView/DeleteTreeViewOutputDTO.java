package org.phyloviz.pwp.administration.service.dtos.tree_views.deleteTreeView;

import lombok.Data;

@Data
public class DeleteTreeViewOutputDTO {
    private final String projectId;
    private final String datasetId;
    private final String treeViewId;
}