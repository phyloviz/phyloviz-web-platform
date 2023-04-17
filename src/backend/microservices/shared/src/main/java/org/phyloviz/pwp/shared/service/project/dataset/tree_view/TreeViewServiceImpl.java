package org.phyloviz.pwp.shared.service.project.dataset.tree_view;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;
import org.phyloviz.pwp.shared.service.dtos.tree_view.TreeViewInfo;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetAccessService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TreeViewServiceImpl implements TreeViewService {

    private final DatasetAccessService datasetAccessService;
    private final TreeViewAccessService treeViewAccessService;

    @Override
    public TreeViewInfo getTreeViewInfo(String treeViewId) {
        return new TreeViewInfo(treeViewAccessService.getTreeViewMetadata(treeViewId));
    }

    @Override
    public void deleteTreeView(String projectId, String datasetId, String treeViewId, String userId) {
        treeViewAccessService.assertExists(projectId, datasetId, treeViewId, userId);

        Dataset dataset = datasetAccessService.getDataset(projectId, datasetId, userId);

        deleteTreeView(treeViewId);

        dataset.getTreeViewIds().remove(treeViewId);
        datasetAccessService.saveDataset(dataset);
    }

    @Override
    public void deleteTreeView(String treeViewId) {
        treeViewAccessService.deleteTreeView(treeViewId);
    }

    @Override
    public GetTreeViewOutput getTreeView(String projectId, String datasetId, String treeViewId, String userId) {
        return treeViewAccessService.getTreeView(projectId, datasetId, treeViewId, userId);
    }
}
