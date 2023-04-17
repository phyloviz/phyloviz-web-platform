package org.phyloviz.pwp.shared.service.project.dataset.tree;

import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;

public interface TreeAccessService {

    TreeMetadata getTreeMetadata(String projectId, String datasetId, String treeId, String userId);

    TreeMetadata getTreeMetadata(String treeId);

    TreeMetadata getTreeMetadataOrNull(String treeId);

    void deleteTree(String treeId);

    void assertExists(String projectId, String datasetId, String treeId, String userId);

    String getTree(String projectId, String datasetId, String treeId, String userId);
}
