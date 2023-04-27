package org.phyloviz.pwp.shared.repository.metadata.tree;

import org.phyloviz.pwp.shared.repository.data.tree.TreeDataRepositoryId;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;

import java.util.List;
import java.util.Optional;

public interface TreeMetadataRepository {

    /**
     * Find any tree metadata from a project id, dataset id and tree id.
     *
     * @param projectId the id of the project
     * @param datasetId the id of the dataset
     * @param treeId    the id of the tree resource
     * @return a tree metadata
     */
    Optional<TreeMetadata> findAnyByProjectIdAndDatasetIdAndTreeId(String projectId, String datasetId, String treeId);

    /**
     * Find all tree metadata from a project id, dataset id and tree id.
     *
     * @param projectId the id of the project
     * @param datasetId the id of the dataset
     * @param treeId    the id of the tree
     * @return a list of tree metadata
     */
    List<TreeMetadata> findAllByProjectIdAndDatasetIdAndTreeId(String projectId, String datasetId, String treeId);

    /**
     * Find all metadata representations of a tree resource.
     *
     * @param treeId the id of the tree resource
     * @return a list of tree metadata
     */
    List<TreeMetadata> findAllByTreeId(String treeId);

    /**
     * Find a tree metadata from its id and repository id.
     *
     * @param treeId       the id of the tree resource
     * @param repositoryId the id of the repository
     * @return a tree metadata
     */
    Optional<TreeMetadata> findByTreeIdAndRepositoryId(String treeId, TreeDataRepositoryId repositoryId);

    /**
     * Find all tree metadata from a dataset id. Only one tree metadata per tree resource.
     *
     * @param datasetId the id of the dataset
     * @return a list of tree metadata
     */
    List<TreeMetadata> findAllByDatasetId(String datasetId);

    /**
     * Deletes a tree metadata.
     *
     * @param treeMetadata the tree metadata to delete
     */
    void delete(TreeMetadata treeMetadata);

    /**
     * Save a tree metadata.
     *
     * @param treeMetadata the tree metadata to save
     * @return the saved tree metadata
     */
    TreeMetadata save(TreeMetadata treeMetadata);

    Optional<TreeMetadata> findByProjectIdAndDatasetIdAndTreeViewIdAndRepositoryId(String projectId, String datasetId,
                                                                                   String treeViewId, TreeDataRepositoryId repositoryId);

    List<TreeMetadata> findAllByProjectIdAndDatasetId(String projectId, String datasetId);

    boolean existsByDatasetIdAndDistanceMatrixIdSource(String datasetId, String distanceMatrixId);

    boolean existsByProjectIdAndDatasetIdAndTreeId(String projectId, String datasetId, String treeId);

}
