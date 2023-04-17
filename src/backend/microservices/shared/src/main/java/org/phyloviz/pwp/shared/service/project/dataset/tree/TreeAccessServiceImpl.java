package org.phyloviz.pwp.shared.service.project.dataset.tree;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree.TreeAdapterFactory;
import org.phyloviz.pwp.shared.adapters.tree.TreeAdapterId;
import org.phyloviz.pwp.shared.adapters.tree.adapter.TreeAdapter;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.service.exceptions.TreeIndexingNeededException;
import org.phyloviz.pwp.shared.service.exceptions.TreeNotFoundException;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetAccessService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeAccessServiceImpl implements TreeAccessService {

    private final DatasetAccessService datasetService;

    private final TreeMetadataRepository treeMetadataRepository;
    private final TreeAdapterFactory treeAdapterFactory;

    @Value("${adapters.get-tree-adapter-priority}")
    private List<TreeAdapterId> getTreeAdapterPriority;

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
    public void deleteTree(String treeId) {
        treeMetadataRepository.findAllByTreeId(treeId)
                .forEach(treeMetadata -> {
                    treeAdapterFactory.getTreeAdapter(treeMetadata.getAdapterId())
                            .deleteTree(treeMetadata.getAdapterSpecificData());

                    treeMetadataRepository.delete(treeMetadata);
                });
    }

    @Override
    public void assertExists(String projectId, String datasetId, String treeId, String userId) {
        getTreeMetadata(projectId, datasetId, treeId, userId);
    }

    @Override
    public String getTree(String projectId, String datasetId, String treeId, String userId) {
        assertExists(projectId, datasetId, treeId, userId);

        List<TreeMetadata> treeMetadataList = treeMetadataRepository.findAllByTreeId(treeId);
        if (treeMetadataList.isEmpty())
            throw new TreeNotFoundException();

        sortByAdapterPriority(treeMetadataList, getTreeAdapterPriority);

        for (TreeMetadata treeMetadata : treeMetadataList) {
            TreeAdapter treeAdapter = treeAdapterFactory.getTreeAdapter(treeMetadata.getAdapterId());

            if (treeAdapter.isFileAdapter())
                continue;

            return treeAdapter.getTree(treeMetadata.getAdapterSpecificData());
        }

        throw new TreeIndexingNeededException("Tree isn't indexed. No metadata with non-file adapter found.");
    }

    private void sortByAdapterPriority(List<TreeMetadata> metadataList, List<TreeAdapterId> adapterPriority) {
        metadataList.sort((o1, o2) -> {
            int i1 = adapterPriority.indexOf(o1.getAdapterId());
            if (i1 == -1)
                i1 = Integer.MAX_VALUE;

            int i2 = adapterPriority.indexOf(o2.getAdapterId());
            if (i2 == -1)
                i2 = Integer.MAX_VALUE;

            return Integer.compare(i1, i2);
        });
    }
}
