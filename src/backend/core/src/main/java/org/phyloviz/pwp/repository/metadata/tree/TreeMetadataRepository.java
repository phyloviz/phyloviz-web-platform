package org.phyloviz.pwp.repository.metadata.tree;

import org.phyloviz.pwp.repository.metadata.tree.documents.TreeMetadata;

import java.util.List;
import java.util.Optional;

public interface TreeMetadataRepository {

    /**
     * Find a tree metadata from a project id, dataset id and tree id.
     *
     * @param projectId the id of the project
     * @param datasetId the id of the dataset
     * @param treeId    the id of the tree resource
     * @return a tree metadata
     */
    Optional<TreeMetadata> findByProjectIdAndDatasetIdAndTreeId(String projectId, String datasetId, String treeId);

    /**
     * Find all tree metadata from a dataset id.
     *
     * @param datasetId the id of the dataset
     * @return a list of tree metadata
     */
    List<TreeMetadata> findAllByDatasetId(String datasetId);

    /**
     * Find all tree metadata from a project id and dataset id.
     *
     * @param projectId the id of the project
     * @param datasetId the id of the dataset
     * @return a list of tree metadata
     */
    List<TreeMetadata> findAllByProjectIdAndDatasetId(String projectId, String datasetId);

    boolean existsByDatasetIdAndDistanceMatrixIdSource(String datasetId, String distanceMatrixId);

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
}
