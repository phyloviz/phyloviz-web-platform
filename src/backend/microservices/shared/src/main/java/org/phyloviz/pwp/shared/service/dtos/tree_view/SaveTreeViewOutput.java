package org.phyloviz.pwp.shared.service.dtos.tree_view;

import lombok.Data;

/**
 * Output for the saveTreeView service.
 */
@Data
public class SaveTreeViewOutput {
    private final String projectId;
    private final String datasetId;
    private final String treeViewId;
}