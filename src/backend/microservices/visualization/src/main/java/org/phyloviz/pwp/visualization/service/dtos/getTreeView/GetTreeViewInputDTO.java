package org.phyloviz.pwp.visualization.service.dtos.getTreeView;

import lombok.Data;


@Data
public class GetTreeViewInputDTO {
    private final String projectId;
    private final String datasetId;
    private final String treeViewId;
}
