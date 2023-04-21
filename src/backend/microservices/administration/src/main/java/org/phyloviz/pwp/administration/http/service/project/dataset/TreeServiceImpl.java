package org.phyloviz.pwp.administration.http.service.project.dataset;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree.TreeAdapterFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.service.dtos.tree.TreeInfo;
import org.phyloviz.pwp.shared.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetMetadataService;
import org.phyloviz.pwp.shared.service.project.dataset.tree.TreeMetadataService;
import org.phyloviz.pwp.shared.service.project.dataset.tree_view.TreeViewMetadataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final DatasetMetadataService datasetMetadataService;
    private final TreeMetadataService treeMetadataService;
    private final TreeViewMetadataService treeViewMetadataService;

    private final TreeAdapterFactory treeAdapterFactory;

    @Override
    public List<TreeInfo> getTreeInfos(String datasetId) {
        return treeMetadataService.getAllTreeMetadataByDatasetId(datasetId).stream()
                .map(TreeInfo::new).toList();
    }

    @Override
    public void deleteTree(String projectId, String datasetId, String treeId, String userId) {
        treeMetadataService.assertExists(projectId, datasetId, treeId, userId);

        Dataset dataset = datasetMetadataService.getDataset(projectId, datasetId, userId);

        dataset.getTreeViewIds().forEach(treeViewId -> {
            TreeViewMetadata treeViewMetadata = treeViewMetadataService.getTreeViewMetadataOrNull(treeViewId);

            if (treeViewMetadata != null && treeViewMetadata.getSource().getTreeId().equals(treeId)) {
                throw new DeniedResourceDeletionException(
                        "Cannot delete tree. It is a dependency of a tree view (treeViewId = " + treeViewId + "). " +
                                "Delete the tree view first."
                );
            }
        });

        deleteTree(treeId);

        dataset.getTreeIds().remove(treeId);
        datasetMetadataService.saveDataset(dataset);
    }

    @Override
    public void deleteTree(String treeId) {
        treeMetadataService.getAllTreeMetadata(treeId)
                .forEach(treeMetadata -> {
                    treeAdapterFactory.getTreeAdapter(treeMetadata.getAdapterId())
                            .deleteTree(treeMetadata.getAdapterSpecificData());

                    treeMetadataService.deleteTree(treeMetadata);
                });
    }
}
