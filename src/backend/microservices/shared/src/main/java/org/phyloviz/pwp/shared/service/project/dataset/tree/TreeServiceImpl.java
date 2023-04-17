package org.phyloviz.pwp.shared.service.project.dataset.tree;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.service.dtos.tree.TreeInfo;
import org.phyloviz.pwp.shared.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetAccessService;
import org.phyloviz.pwp.shared.service.project.dataset.tree_view.TreeViewAccessService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final DatasetAccessService datasetAccessService;
    private final TreeAccessService treeAccessService;
    private final TreeViewAccessService treeViewAccessService;

    @Override
    public TreeInfo getTreeInfo(String treeId) {
        return new TreeInfo(treeAccessService.getTreeMetadata(treeId));
    }

    @Override
    public void deleteTree(String projectId, String datasetId, String treeId, String userId) {
        treeAccessService.assertExists(projectId, datasetId, treeId, userId);

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
