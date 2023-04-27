package org.phyloviz.pwp.shared.repository.metadata.tree_view;

import org.phyloviz.pwp.shared.repository.data.tree_view.TreeViewDataRepositoryId;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;

import java.util.List;
import java.util.Optional;

public interface TreeViewMetadataRepository {

    /**
     * Find any tree view metadata from a project id, dataset id and tree view id.
     *
     * @param projectId  the id of the project
     * @param datasetId  the id of the dataset
     * @param treeViewId the id of the tree view resource
     * @return a tree view metadata
     */
    Optional<TreeViewMetadata> findAnyByProjectIdAndDatasetIdAndTreeViewId(String projectId, String datasetId, String treeViewId);

    /**
     * Find all tree view metadata from a project id, dataset id and tree view id.
     *
     * @param projectId  the id of the project
     * @param datasetId  the id of the dataset
     * @param treeViewId the id of the tree view
     * @return a list of tree view metadata
     */
    List<TreeViewMetadata> findAllByProjectIdAndDatasetIdAndTreeViewId(String projectId, String datasetId, String treeViewId);

    /**
     * Find all metadata representations of a tree view resource.
     *
     * @param treeViewId the id of the tree view resource
     * @return a list of tree view metadata
     */
    List<TreeViewMetadata> findAllByTreeViewId(String treeViewId);

    /**
     * Find a tree view metadata from its id and repository id.
     *
     * @param treeViewId   the id of the tree view resource
     * @param repositoryId the id of the repository
     * @return a tree view metadata
     */
    Optional<TreeViewMetadata> findByTreeViewIdAndRepositoryId(String treeViewId, TreeViewDataRepositoryId repositoryId);

    Optional<TreeViewMetadata> findByProjectIdAndDatasetIdAndTreeViewIdAndRepositoryId(String projectId, String datasetId,
                                                                                       String treeViewId, TreeViewDataRepositoryId repositoryId);

    List<TreeViewMetadata> findAllByProjectIdAndDatasetId(String projectId, String datasetId);

    /**
     * Find all tree view metadata from a dataset id. Only one tree view metadata per tree view resource.
     *
     * @param datasetId the id of the dataset
     * @return a list of tree view metadata
     */
    List<TreeViewMetadata> findAllByDatasetId(String datasetId);

    /**
     * Deletes a tree view metadata.
     *
     * @param treeViewMetadata the tree view metadata to delete
     */
    void delete(TreeViewMetadata treeViewMetadata);

    /**
     * Save a tree view metadata.
     *
     * @param treeViewMetadata the tree view metadata to save
     * @return the saved tree view metadata
     */
    TreeViewMetadata save(TreeViewMetadata treeViewMetadata);

    boolean existsByDatasetIdAndTreeIdSource(String datasetId, String treeId);

    boolean existsByProjectIdAndDatasetIdAndTreeViewId(String projectId, String datasetId, String treeViewId);
}
