package org.phyloviz.pwp.shared.service.project.dataset.tree;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.service.adapters.tree.TreeAdapter;
import org.phyloviz.pwp.shared.service.dtos.tree.TreeMetadataDTO;
import org.phyloviz.pwp.shared.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.shared.service.exceptions.TreeIndexingNeededException;
import org.phyloviz.pwp.shared.service.exceptions.TreeNotFoundException;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetAccessService;
import org.phyloviz.pwp.shared.service.project.dataset.treeView.TreeViewAccessService;
import org.phyloviz.pwp.shared.service.project.dataset.treeView.TreeViewService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final DatasetAccessService datasetAccessService;
    private final TreeAccessService treeAccessService;
    private final TreeViewAccessService treeViewAccessService;

    @Override
    public TreeMetadata getTreeMetadata(String projectId, String datasetId, String treeId, String userId) {
        return treeAccessService.getTreeMetadata(projectId, datasetId, treeId, userId);
    }

    @Override
    public TreeMetadata getTreeMetadata(String treeId) {
        return treeAccessService.getTreeMetadata(treeId);
    }

    @Override
    public TreeMetadata getTreeMetadataOrNull(String treeId) {
        return treeAccessService.getTreeMetadataOrNull(treeId);
    }

    @Override
    public TreeMetadataDTO getTreeMetadataDTO(String treeId) {
        return new TreeMetadataDTO(getTreeMetadata(treeId));
    }

    @Override
    public void assertExists(String projectId, String datasetId, String treeId, String userId) {
        treeAccessService.assertExists(projectId, datasetId, treeId, userId);
    }

    @Override
    public void deleteTree(String projectId, String datasetId, String treeId, String userId) {
        assertExists(projectId, datasetId, treeId, userId);

        Dataset dataset = datasetAccessService.getDataset(projectId, datasetId, userId);

        dataset.getTreeViewIds().forEach(treeViewId -> {
            TreeViewMetadata treeViewMetadata = treeViewAccessService.getTreeViewMetadataOrNull(treeViewId);

            if (treeViewMetadata != null && treeViewMetadata.getSource().getTreeId().equals(treeId)) {
                throw new DeniedResourceDeletionException(
                        "Cannot delete tree. It is a dependency of a tree view (treeViewId = " + treeViewId + "). " +
                                "Delete the tree view first."
                );
            }
        });

        deleteTree(treeId);

        dataset.getTreeIds().remove(treeId);
        datasetAccessService.saveDataset(dataset);
    }

    @Override
    public void deleteTree(String treeId) {
        treeAccessService.deleteTree(treeId);
    }

    @Override
    public String getTree(String projectId, String datasetId, String treeId, String userId) {
        return treeAccessService.getTree(projectId, datasetId, treeId, userId);
    }
}
