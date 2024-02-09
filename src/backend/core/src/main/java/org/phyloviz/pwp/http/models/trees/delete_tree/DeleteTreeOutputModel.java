package org.phyloviz.pwp.http.models.trees.delete_tree;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteTreeOutputModel {
    private String projectId;
    private String datasetId;
    private String treeId;
}
