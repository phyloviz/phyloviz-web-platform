package org.phyloviz.pwp.visualization.service.projects.datasets.treeViews;

import org.phyloviz.pwp.shared.domain.User;
import org.phyloviz.pwp.visualization.service.dtos.getTreeView.GetTreeViewOutput;

public interface TreeViewVisualizationService {

    /**
     * Gets a tree view, given its id.
     *
     * @return the tree
     */
    GetTreeViewOutput getTreeView(
            String projectId,
            String datasetId,
            String treeViewId,
            User user
    );
}
