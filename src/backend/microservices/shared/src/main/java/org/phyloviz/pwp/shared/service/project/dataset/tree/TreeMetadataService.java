package org.phyloviz.pwp.shared.service.project.dataset.tree;

import org.phyloviz.pwp.shared.adapters.tree.TreeAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;

import java.util.List;

public interface TreeMetadataService {

    TreeMetadata getTreeMetadata(String projectId, String datasetId, String treeId, String userId);

    TreeMetadata getTreeMetadata(String treeId);

    TreeMetadata getTreeMetadataOrNull(String treeId);

    TreeMetadata getTreeMetadataByAdapterIdOrNull(String treeId, TreeAdapterId adapterId);

    List<TreeMetadata> getAllTreeMetadata(String treeId);

    /**
     * Find all tree metadata from a dataset id. Only one tree metadata per tree resource.
     *
     * @param datasetId the id of the dataset
     * @return a list of tree metadata
     */
    List<TreeMetadata> getAllTreeMetadataByDatasetId(String datasetId);

    void deleteTree(TreeMetadata treeMetadata);

    void assertExists(String projectId, String datasetId, String treeId, String userId);
}
