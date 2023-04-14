package org.phyloviz.pwp.visualization.service.projects.datasets.treeViews;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.repository.metadata.treeView.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TreeViewNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.phyloviz.pwp.shared.adapters.treeView.TreeViewAdapter;
import org.phyloviz.pwp.shared.adapters.treeView.TreeViewAdapterFactory;
import org.phyloviz.pwp.visualization.service.dtos.getTreeView.GetTreeViewInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getTreeView.GetTreeViewOutputDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeViewVisualizationServiceImpl implements TreeViewVisualizationService {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;
    private final TreeViewMetadataRepository treeViewMetadataRepository;

    private final TreeViewAdapterFactory treeViewAdapterFactory;

    private final List<String> getTreeViewAdapterPriority = List.of("phylodb");

    @Override
    public GetTreeViewOutputDTO getTreeView(GetTreeViewInputDTO getTreeViewInputDTO) {
        String projectId = getTreeViewInputDTO.getProjectId();
        String datasetId = getTreeViewInputDTO.getDatasetId();
        String treeViewId = getTreeViewInputDTO.getTreeViewId();

        validateParameters(projectId, datasetId, treeViewId, getTreeViewInputDTO.getUser().getId());

        TreeViewMetadata treeViewMetadata = getPrioritySortedTreeViewMetadataList(treeViewId).get(0);

        TreeViewAdapter treeViewAdapter = treeViewAdapterFactory.getTreeViewAdapter(treeViewMetadata.getAdapterId());

        return new GetTreeViewOutputDTO(treeViewAdapter.getTreeView(treeViewMetadata.getAdapterSpecificData()));
    }

    private List<TreeViewMetadata> getPrioritySortedTreeViewMetadataList(String treeViewId) {
        List<TreeViewMetadata> treeViewMetadataList = treeViewMetadataRepository.findAllByTreeViewId(treeViewId);
        if (treeViewMetadataList.isEmpty())
            throw new TreeViewNotFoundException();

        treeViewMetadataList.sort((o1, o2) -> {
            int i1 = getTreeViewAdapterPriority.indexOf(o1.getAdapterId());
            if (i1 == -1)
                i1 = Integer.MAX_VALUE;

            int i2 = getTreeViewAdapterPriority.indexOf(o2.getAdapterId());
            if (i2 == -1)
                i2 = Integer.MAX_VALUE;

            return Integer.compare(i1, i2);
        });

        return treeViewMetadataList;
    }

    private void validateParameters(String projectId, String datasetId, String treeViewId, String userId) {
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException();

        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(DatasetNotFoundException::new);

        if (!dataset.getProjectId().equals(projectId))
            throw new DatasetNotFoundException();

        dataset
                .getTreeViewIds().stream()
                .filter(treeViewId::equals)
                .findAny()
                .orElseThrow(TreeViewNotFoundException::new);
    }
}
