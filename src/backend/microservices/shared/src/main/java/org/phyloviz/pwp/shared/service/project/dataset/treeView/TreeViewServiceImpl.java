package org.phyloviz.pwp.shared.service.project.dataset.treeView;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.treeView.TreeViewAdapter;
import org.phyloviz.pwp.shared.adapters.treeView.TreeViewAdapterFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.treeView.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.service.dtos.treeView.GetTreeViewOutput;
import org.phyloviz.pwp.shared.service.dtos.treeView.TreeViewMetadataDTO;
import org.phyloviz.pwp.shared.service.exceptions.TreeViewNotFoundException;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeViewServiceImpl implements TreeViewService {

    private final TreeViewMetadataRepository treeViewMetadataRepository;

    private final DatasetService datasetService;

    private final TreeViewAdapterFactory treeViewAdapterFactory;

    private final List<String> getTreeViewAdapterPriority = List.of("phylodb");

    @Override
    public TreeViewMetadata getTreeViewMetadata(String projectId, String datasetId, String treeViewId, String userId) {
        Dataset dataset = datasetService.getDataset(projectId, datasetId, userId);

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
    public TreeViewMetadataDTO getTreeViewMetadataDTO(String treeViewId) {
        return new TreeViewMetadataDTO(getTreeViewMetadata(treeViewId));
    }

    @Override
    public void assertExists(String projectId, String datasetId, String treeViewId, String userId) {
        getTreeViewMetadata(projectId, datasetId, treeViewId, userId);
    }

    @Override
    public void deleteTreeView(String projectId, String datasetId, String treeViewId, String userId) {
        assertExists(projectId, datasetId, treeViewId, userId);

        Dataset dataset = datasetService.getDataset(projectId, datasetId, userId);

        deleteTreeView(treeViewId);

        dataset.getTreeViewIds().remove(treeViewId);
        datasetService.saveDataset(dataset);
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

    private void sortByAdapterPriority(List<TreeViewMetadata> metadataList, List<String> adapterPriority) {
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
