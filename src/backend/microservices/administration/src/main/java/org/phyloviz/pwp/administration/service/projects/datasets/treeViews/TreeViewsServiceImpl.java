package org.phyloviz.pwp.administration.service.projects.datasets.treeViews;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.repository.data.FileStorageRepository;
import org.phyloviz.pwp.administration.service.dtos.treeViews.TreeViewDTO;
import org.phyloviz.pwp.administration.service.dtos.treeViews.deleteTreeView.DeleteTreeViewInputDTO;
import org.phyloviz.pwp.administration.service.dtos.treeViews.deleteTreeView.DeleteTreeViewOutputDTO;
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
import org.springframework.stereotype.Service;

/**
 * Implementation of the{@link TreeViewsService} interface.
 */
@Service
@RequiredArgsConstructor
public class TreeViewsServiceImpl implements TreeViewsService {

    private final TreeViewMetadataRepository treeViewMetadataRepository;
    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;
    private final FileStorageRepository fileStorageRepository;

    @Override
    public DeleteTreeViewOutputDTO deleteTreeView(DeleteTreeViewInputDTO deleteTreeViewInputDTO) {
        String projectId = deleteTreeViewInputDTO.getProjectId();
        String datasetId = deleteTreeViewInputDTO.getDatasetId();
        String treeViewId = deleteTreeViewInputDTO.getTreeViewId();
        String userId = deleteTreeViewInputDTO.getUser().getId();

        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException();

        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(DatasetNotFoundException::new);

        treeViewMetadataRepository.findByTreeViewId(treeViewId).orElseThrow(TreeViewNotFoundException::new);

        deleteTreeView(treeViewId);

        dataset.getTreeViewIds().remove(treeViewId);
        datasetRepository.save(dataset);

        return new DeleteTreeViewOutputDTO(projectId, datasetId, treeViewId);
    }

    @Override
    public void deleteTreeView(String treeViewId) {
        treeViewMetadataRepository.findAllByTreeViewId(treeViewId)
                .forEach(treeViewMetadata -> {
                    fileStorageRepository.delete(treeViewMetadata.getUrl());
                    treeViewMetadataRepository.deleteTreeView(treeViewMetadata);
                });
    }

    @Override
    public TreeViewDTO getTreeView(String treeViewId) {
        TreeViewMetadata treeViewMetadata =
                treeViewMetadataRepository
                        .findByTreeViewId(treeViewId)
                        .orElseThrow(TreeViewNotFoundException::new);

        return new TreeViewDTO(treeViewMetadata);
    }
}
