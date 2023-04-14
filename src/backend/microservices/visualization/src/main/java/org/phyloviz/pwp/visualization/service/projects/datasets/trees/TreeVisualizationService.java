package org.phyloviz.pwp.visualization.service.projects.datasets.trees;

import org.phyloviz.pwp.visualization.service.dtos.getTree.GetTreeInputDTO;

public interface TreeVisualizationService {

    /**
     * Gets a tree, given its id.
     *
     * @param getTreeInputDTO the input DTO
     * @return the tree
     */
    String getTree(GetTreeInputDTO getTreeInputDTO);
}
