package org.phyloviz.pwp.administration.service.dtos.trees.deleteTree;

import lombok.Data;

@Data
public class DeleteTreeOutputDTO {
    private final String projectId;
    private final String datasetId;
    private final String treeId;
}
