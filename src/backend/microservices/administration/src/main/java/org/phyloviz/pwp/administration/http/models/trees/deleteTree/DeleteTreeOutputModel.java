package org.phyloviz.pwp.administration.http.models.trees.deleteTree;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteTreeOutputModel {
    private String projectId;
    private String datasetId;
    private String treeId;
}
