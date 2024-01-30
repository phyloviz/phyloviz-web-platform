package org.phyloviz.pwp.service.project.dataset.tree;

import org.phyloviz.pwp.service.dtos.tree.TreeInfo;
import org.phyloviz.pwp.service.dtos.tree.UpdateTreeOutput;

import java.util.List;

/**
 * Service for managing trees.
 */
public interface TreeService {

    /**
     * Returns a list of tree infos for the given dataset.
     *
     * @param datasetId the dataset id
     * @return a list of tree infos for the given dataset
     */
    List<TreeInfo> getTreeInfos(String datasetId);

    /**
     * Deletes a tree.
     *
     * @param projectId the project id
     * @param datasetId the dataset id
     * @param treeId    the tree id
     * @param userId    the user id
     */
    void deleteTree(String projectId, String datasetId, String treeId, String userId);

    /**
     * Deletes all trees of a dataset.
     *
     * @param projectId the project id
     * @param datasetId the dataset id
     */
    void deleteAllByProjectIdAndDatasetId(String projectId, String datasetId);

    /**
     * Updates a tree.
     *
     * @param name      the name of the tree
     * @param projectId the project id
     * @param datasetId the dataset id
     * @param treeId    the tree id
     * @param userId    the user id
     * @return the output of the tree update
     */
    UpdateTreeOutput updateTree(String name, String projectId, String datasetId, String treeId, String userId);
}
