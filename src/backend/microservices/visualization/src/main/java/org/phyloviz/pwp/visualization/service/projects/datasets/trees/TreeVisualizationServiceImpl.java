package org.phyloviz.pwp.visualization.service.projects.datasets.trees;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TreeNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.phyloviz.pwp.shared.adapters.tree.TreeAdapter;
import org.phyloviz.pwp.shared.adapters.tree.TreeAdapterFactory;
import org.phyloviz.pwp.visualization.service.dtos.getTree.GetTreeInputDTO;
import org.phyloviz.pwp.visualization.service.exceptions.TreeIndexingNeededException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeVisualizationServiceImpl implements TreeVisualizationService {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;
    private final TreeMetadataRepository treeMetadataRepository;

    private final TreeAdapterFactory treeAdapterFactory;

    private final List<String> getTreeAdapterPriority = List.of("phylodb");

    @Override
    public String getTree(GetTreeInputDTO getTreeInputDTO) {
        String projectId = getTreeInputDTO.getProjectId();
        String datasetId = getTreeInputDTO.getDatasetId();
        String treeId = getTreeInputDTO.getTreeId();

        validateParameters(projectId, datasetId, treeId, getTreeInputDTO.getUser().getId());

        List<TreeMetadata> treeMetadataList = getPrioritySortedTreeMetadataList(treeId);

        for(TreeMetadata treeMetadata : treeMetadataList) {
            TreeAdapter treeAdapter = treeAdapterFactory.getTreeAdapter(treeMetadata.getAdapterId());

            if(treeAdapter.isFileAdapter())
                continue;

            return treeAdapter.getTree(treeMetadata.getAdapterSpecificData());
        }

        throw new TreeIndexingNeededException("Tree isn't indexed. No (non-file) adapter found.");
    }

    private List<TreeMetadata> getPrioritySortedTreeMetadataList(String treeId) {
        List<TreeMetadata> treeMetadataList = treeMetadataRepository.findAllByTreeId(treeId);
        if(treeMetadataList.isEmpty())
            throw new TreeNotFoundException();

        treeMetadataList.sort((o1, o2) -> {
            int i1 = getTreeAdapterPriority.indexOf(o1.getAdapterId());
            if (i1 == -1)
                i1 = Integer.MAX_VALUE;

            int i2 = getTreeAdapterPriority.indexOf(o2.getAdapterId());
            if (i2 == -1)
                i2 = Integer.MAX_VALUE;

            return Integer.compare(i1, i2);
        });

        return treeMetadataList;
    }

    private void validateParameters(String projectId, String datasetId, String treeId, String userId) {
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException();

        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(DatasetNotFoundException::new);

        if(!dataset.getProjectId().equals(projectId))
            throw new DatasetNotFoundException();

        dataset
                .getTreeIds().stream()
                .filter(treeId::equals)
                .findAny()
                .orElseThrow(TreeNotFoundException::new);
    }
}
