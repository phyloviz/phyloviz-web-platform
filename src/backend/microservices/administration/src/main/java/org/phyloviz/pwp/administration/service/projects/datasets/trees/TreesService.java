package org.phyloviz.pwp.administration.service.projects.datasets.trees;

import org.phyloviz.pwp.administration.service.dtos.trees.TreeDTO;
import org.phyloviz.pwp.administration.service.dtos.trees.deleteTree.DeleteTreeInputDTO;
import org.phyloviz.pwp.administration.service.dtos.trees.deleteTree.DeleteTreeOutputDTO;

public interface TreesService {

    /**
     * Deletes a tree.
     *
     * @param deleteTreeInputDTO the input data for the tree deletion
     * @return the output data for the tree deletion
     */
    DeleteTreeOutputDTO deleteTree(DeleteTreeInputDTO deleteTreeInputDTO);

    /**
     * Deletes a tree.
     * This method is also used by other services (DatasetsService) to allow for the recursive deletion of resources.
     *
     * @param treeId id of the tree
     */
    void deleteTree(String treeId);

    /**
     * Gets a tree.
     * This method is also used by other services (DatasetsService) to allow for the recursive retrieval of resources.
     *
     * @param treeId id of the tree
     * @return the tree
     */
    TreeDTO getTree(String treeId);
}
