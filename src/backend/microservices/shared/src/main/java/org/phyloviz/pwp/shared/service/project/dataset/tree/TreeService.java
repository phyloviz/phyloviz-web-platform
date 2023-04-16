package org.phyloviz.pwp.shared.service.project.dataset.tree;

import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.service.dtos.tree.TreeMetadataDTO;

public interface TreeService {

    TreeMetadata getTreeMetadata(String projectId, String datasetId, String treeId, String userId);

    TreeMetadata getTreeMetadata(String treeId);

    TreeMetadata getTreeMetadataOrNull(String treeId);

    TreeMetadataDTO getTreeMetadataDTO(String treeId);

    void assertExists(String projectId, String datasetId, String treeId, String userId);

    void deleteTree(String projectId, String datasetId, String treeId, String userId);

    void deleteTree(String treeId);

    String getTree(String projectId, String datasetId, String treeId, String userId);
}
