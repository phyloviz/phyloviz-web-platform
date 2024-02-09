package org.phyloviz.pwp.service.project.dataset.tree_view;

import org.phyloviz.pwp.service.dtos.tree_view.TreeViewInfo;
import org.phyloviz.pwp.service.dtos.tree_view.UpdateTreeViewOutput;

import java.util.List;

/**
 * Service for managing tree views.
 */
public interface TreeViewService {

    /**
     * Returns a list of tree view infos for the given dataset.
     *
     * @param datasetId the dataset id
     * @return a list of tree view infos for the given dataset
     */
    List<TreeViewInfo> getTreeViewInfos(String datasetId);

    /**
     * Deletes a tree view.
     *
     * @param projectId  the project id
     * @param datasetId  the dataset id
     * @param treeViewId the tree view id
     * @param userId     the user id
     */
    void deleteTreeView(String projectId, String datasetId, String treeViewId, String userId);

    /**
     * Deletes all tree views of a dataset.
     *
     * @param projectId the project id
     * @param datasetId the dataset id
     */
    void deleteAllByProjectIdAndDatasetId(String projectId, String datasetId);

    /**
     * Updates a tree view.
     *
     * @param name       the name of the tree view
     * @param projectId  the project id
     * @param datasetId  the dataset id
     * @param treeViewId the tree view id
     * @param userId     the user id
     * @return the output of the tree view update
     */
    UpdateTreeViewOutput updateTreeView(String name, String projectId, String datasetId, String treeViewId, String userId);
}
