package org.phyloviz.pwp.shared.service.project.dataset.treeView;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.treeView.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewAdapterId;
import org.phyloviz.pwp.shared.service.adapters.treeView.TreeViewAdapter;
import org.phyloviz.pwp.shared.service.adapters.treeView.TreeViewAdapterFactory;
import org.phyloviz.pwp.shared.service.dtos.treeView.GetTreeViewOutput;
import org.phyloviz.pwp.shared.service.exceptions.TreeViewNotFoundException;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetAccessService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeViewAccessServiceImpl implements TreeViewAccessService {

    private final DatasetAccessService datasetAccessService;

    private final TreeViewMetadataRepository treeViewMetadataRepository;
    private final TreeViewAdapterFactory treeViewAdapterFactory;

    @Value("${adapters.get-tree-view-adapter-priority}")
    private List<TreeViewAdapterId> getTreeViewAdapterPriority;

    @Override
    public TreeViewMetadata getTreeViewMetadata(String projectId, String datasetId, String treeViewId, String userId) {
        Dataset dataset = datasetAccessService.getDataset(projectId, datasetId, userId);

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
    public void deleteTreeView(String treeViewId) {
        treeViewMetadataRepository.findAllByTreeViewId(treeViewId)
                .forEach(treeViewMetadata -> {
                    treeViewAdapterFactory.getTreeViewAdapter(treeViewMetadata.getAdapterId())
                            .deleteTreeView(treeViewMetadata.getAdapterSpecificData());

                    treeViewMetadataRepository.delete(treeViewMetadata);
                });
    }

    @Override
    public void assertExists(String projectId, String datasetId, String treeViewId, String userId) {
        getTreeViewMetadata(projectId, datasetId, treeViewId, userId);
    }

    @Override
    public GetTreeViewOutput getTreeView(String projectId, String datasetId, String treeViewId, String userId) {
        assertExists(projectId, datasetId, treeViewId, userId);

        List<TreeViewMetadata> treeViewMetadataList = treeViewMetadataRepository.findAllByTreeViewId(treeViewId);
        if (treeViewMetadataList.isEmpty())
            throw new TreeViewNotFoundException();

        sortByAdapterPriority(treeViewMetadataList, getTreeViewAdapterPriority);

        TreeViewMetadata treeViewMetadata = treeViewMetadataList.get(0);

        TreeViewAdapter treeViewAdapter = treeViewAdapterFactory.getTreeViewAdapter(treeViewMetadata.getAdapterId());

        return treeViewAdapter.getTreeView(treeViewMetadata.getAdapterSpecificData());
    }

    private void sortByAdapterPriority(List<TreeViewMetadata> metadataList, List<TreeViewAdapterId> adapterPriority) {
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
