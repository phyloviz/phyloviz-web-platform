package org.phyloviz.pwp.shared.service.project.dataset.tree;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree.TreeAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.service.exceptions.TreeNotFoundException;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetMetadataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeMetadataServiceImpl implements TreeMetadataService {

    private final DatasetMetadataService datasetService;

    private final TreeMetadataRepository treeMetadataRepository;

    @Override
    public TreeMetadata getTreeMetadata(String projectId, String datasetId, String treeId, String userId) {
        Dataset dataset = datasetService.getDataset(projectId, datasetId, userId);

        if (!dataset.getTreeIds().contains(treeId)) {
            throw new TreeNotFoundException();
        }

        return treeMetadataRepository.findByTreeId(treeId).orElseThrow(TreeNotFoundException::new);
    }

    @Override
    public TreeMetadata getTreeMetadata(String treeId) {
        return treeMetadataRepository.findByTreeId(treeId).orElseThrow(TreeNotFoundException::new);
    }

    @Override
    public TreeMetadata getTreeMetadataOrNull(String treeId) {
        return treeMetadataRepository.findByTreeId(treeId).orElse(null);
    }

    @Override
    public TreeMetadata getTreeMetadataByAdapterIdOrNull(String treeId, TreeAdapterId adapterId) {
        return treeMetadataRepository
                .findByTreeIdAndAdapterId(treeId, adapterId)
                .orElse(null);
    }

    @Override
    public List<TreeMetadata> getAllTreeMetadata(String treeId) {
        return treeMetadataRepository.findAllByTreeId(treeId);
    }

    @Override
    public List<TreeMetadata> getAllTreeMetadataByDatasetId(String datasetId) {
        return treeMetadataRepository.findAllByDatasetId(datasetId);
    }

    @Override
    public void deleteTree(TreeMetadata treeMetadata) {
        treeMetadataRepository.delete(treeMetadata);
    }

    @Override
    public void assertExists(String projectId, String datasetId, String treeId, String userId) {
        getTreeMetadata(projectId, datasetId, treeId, userId);
    }
}
