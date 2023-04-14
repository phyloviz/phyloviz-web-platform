package org.phyloviz.pwp.administration.service.projects.datasets.treeViews;

import org.phyloviz.pwp.administration.service.dtos.treeViews.TreeViewDTO;
import org.phyloviz.pwp.administration.service.dtos.treeViews.deleteTreeView.DeleteTreeViewInputDTO;
import org.phyloviz.pwp.administration.service.dtos.treeViews.deleteTreeView.DeleteTreeViewOutputDTO;

/**
 * Service for operations related to tree views.
 */
public interface TreeViewsService {

    /**
     * Deletes a tree view.
     *
     * @param deleteTreeViewInputDTO the input data for the tree view deletion
     * @return the output data for the tree view deletion
     */
    DeleteTreeViewOutputDTO deleteTreeView(DeleteTreeViewInputDTO deleteTreeViewInputDTO);

    /**
     * Deletes a tree view.
     * This method is also used by other services (DatasetsService) to allow for the recursive deletion of resources.
     *
     * @param treeViewId id of the tree view
     */
    void deleteTreeView(String treeViewId);

    /**
     * Gets a tree view.
     * This method is also used by other services (DatasetsService) to allow for the recursive retrieval of resources.
     * Does not delete its own id from the dataset.
     *
     * @param treeViewId id of the tree view
     * @return the tree view
     */
    TreeViewDTO getTreeView(String treeViewId);
}
