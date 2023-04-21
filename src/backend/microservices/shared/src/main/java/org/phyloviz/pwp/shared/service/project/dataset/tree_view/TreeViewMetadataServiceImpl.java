package org.phyloviz.pwp.shared.service.project.dataset.tree_view;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree_view.TreeViewAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.service.exceptions.TreeViewNotFoundException;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetMetadataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeViewMetadataServiceImpl implements TreeViewMetadataService {

    private final DatasetMetadataService datasetMetadataService;

    private final TreeViewMetadataRepository treeViewMetadataRepository;

    @Override
    public TreeViewMetadata getTreeViewMetadata(String projectId, String datasetId, String treeViewId, String userId) {
        Dataset dataset = datasetMetadataService.getDataset(projectId, datasetId, userId);

        if (!dataset.getTreeViewIds().contains(treeViewId)) {
            throw new TreeViewNotFoundException();
        }

        return treeViewMetadataRepository.findByTreeViewId(treeViewId).orElseThrow(TreeViewNotFoundException::new);
    }

    @Override
    public TreeViewMetadata getTreeViewMetadata(String treeViewId) {
        return treeViewMetadataRepository.findByTreeViewId(treeViewId).orElseThrow(TreeViewNotFoundException::new);
    }

    @Override
    public TreeViewMetadata getTreeViewMetadataOrNull(String treeViewId) {
        return treeViewMetadataRepository.findByTreeViewId(treeViewId).orElse(null);
    }

    @Override
    public TreeViewMetadata getTreeViewMetadataByAdapterIdOrNull(String treeViewId, TreeViewAdapterId adapterId) {
        return treeViewMetadataRepository
                .findByTreeViewIdAndAdapterId(treeViewId, adapterId)
                .orElse(null);
    }

    @Override
    public List<TreeViewMetadata> getAllTreeViewMetadata(String treeViewId) {
        return treeViewMetadataRepository.findAllByTreeViewId(treeViewId);
    }

    @Override
    public List<TreeViewMetadata> getAllTreeViewMetadataByDatasetId(String datasetId) {
        return treeViewMetadataRepository.findAllByDatasetId(datasetId);
    }

    @Override
    public void deleteTreeView(TreeViewMetadata treeViewMetadata) {
        treeViewMetadataRepository.delete(treeViewMetadata);
    }

    @Override
    public void assertExists(String projectId, String datasetId, String treeViewId, String userId) {
        getTreeViewMetadata(projectId, datasetId, treeViewId, userId);
    }
}
