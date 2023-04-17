package org.phyloviz.pwp.shared.service.project.dataset.treeView;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.treeView.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewAdapterId;
import org.phyloviz.pwp.shared.service.adapters.treeView.TreeViewAdapter;
import org.phyloviz.pwp.shared.service.adapters.treeView.TreeViewAdapterFactory;
import org.phyloviz.pwp.shared.service.dtos.treeView.GetTreeViewOutput;
import org.phyloviz.pwp.shared.service.dtos.treeView.TreeViewMetadataDTO;
import org.phyloviz.pwp.shared.service.exceptions.TreeViewNotFoundException;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetAccessService;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeViewServiceImpl implements TreeViewService {

    private final DatasetAccessService datasetAccessService;
    private final TreeViewAccessService treeViewAccessService;

    @Override
    public TreeViewMetadata getTreeViewMetadata(String projectId, String datasetId, String treeViewId, String userId) {
        return treeViewAccessService.getTreeViewMetadata(projectId, datasetId, treeViewId, userId);
    }

    @Override
    public TreeViewMetadata getTreeViewMetadata(String treeViewId) {
        return treeViewAccessService.getTreeViewMetadata(treeViewId);
    }

    @Override
    public TreeViewMetadata getTreeViewMetadataOrNull(String treeViewId) {
        return treeViewAccessService.getTreeViewMetadataOrNull(treeViewId);
    }

    @Override
    public TreeViewMetadataDTO getTreeViewMetadataDTO(String treeViewId) {
        return new TreeViewMetadataDTO(getTreeViewMetadata(treeViewId));
    }

    @Override
    public void assertExists(String projectId, String datasetId, String treeViewId, String userId) {
        treeViewAccessService.assertExists(projectId, datasetId, treeViewId, userId);
    }

    @Override
    public void deleteTreeView(String projectId, String datasetId, String treeViewId, String userId) {
        assertExists(projectId, datasetId, treeViewId, userId);

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
