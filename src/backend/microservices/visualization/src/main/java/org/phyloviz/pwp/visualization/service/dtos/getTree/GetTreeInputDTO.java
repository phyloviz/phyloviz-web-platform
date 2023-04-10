package org.phyloviz.pwp.visualization.service.dtos.getTree;

import lombok.Data;


@Data
public class GetTreeInputDTO {
    private final String projectId;
    private final String datasetId;
    private final String treeId;
}
