package org.phyloviz.pwp.administration.http.service.project.dataset;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree_view.TreeViewAdapterFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.service.dtos.tree_view.TreeViewInfo;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetMetadataService;
import org.phyloviz.pwp.shared.service.project.dataset.tree_view.TreeViewMetadataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeViewServiceImpl implements TreeViewService {

    private final DatasetMetadataService datasetMetadataService;
    private final TreeViewMetadataService treeViewMetadataService;

    private final TreeViewAdapterFactory treeViewAdapterFactory;

    @Override
    public List<TreeViewInfo> getTreeViewInfos(String datasetId) {
        return treeViewMetadataService.getAllTreeViewMetadataByDatasetId(datasetId).stream()
                .map(TreeViewInfo::new).toList();
    }

    @Override
    public void deleteTreeView(String projectId, String datasetId, String treeViewId, String userId) {
        treeViewMetadataService.assertExists(projectId, datasetId, treeViewId, userId);

        Dataset dataset = datasetMetadataService.getDataset(projectId, datasetId, userId);

        deleteTreeView(treeViewId);

        dataset.getTreeViewIds().remove(treeViewId);
        datasetMetadataService.saveDataset(dataset);
    }

    @Override
    public void deleteTreeView(String treeViewId) {
        treeViewMetadataService.getAllTreeViewMetadata(treeViewId)
                .forEach(treeViewMetadata -> {
                    treeViewAdapterFactory.getTreeViewAdapter(treeViewMetadata.getAdapterId())
                            .deleteTreeView(treeViewMetadata.getAdapterSpecificData());

                    treeViewMetadataService.deleteTreeView(treeViewMetadata);
                });
    }
}
