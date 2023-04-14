package org.phyloviz.pwp.visualization.service.projects.datasets.treeViews;

import org.phyloviz.pwp.visualization.service.dtos.getTreeView.GetTreeViewInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getTreeView.GetTreeViewOutputDTO;

public interface TreeViewVisualizationService {

    /**
     * Gets a tree view, given its id.
     *
     * @param getTreeViewInputDTO the input DTO
     * @return the tree
     */
    GetTreeViewOutputDTO getTreeView(GetTreeViewInputDTO getTreeViewInputDTO);
}
