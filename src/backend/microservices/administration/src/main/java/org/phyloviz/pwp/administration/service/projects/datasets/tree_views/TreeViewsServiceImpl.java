package org.phyloviz.pwp.administration.service.projects.datasets.tree_views;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.repository.data.FileStorageRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.administration.service.dtos.tree_views.TreeViewDTO;
import org.phyloviz.pwp.administration.service.dtos.tree_views.deleteTreeView.DeleteTreeViewInputDTO;
import org.phyloviz.pwp.administration.service.dtos.tree_views.deleteTreeView.DeleteTreeViewOutputDTO;
import org.phyloviz.pwp.shared.repository.metadata.treeView.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TreeViewsServiceImpl implements TreeViewsService {

    private final TreeViewMetadataRepository treeViewMetadataRepository;
    private final ProjectRepository projectRepository;
    private final FileStorageRepository fileStorageRepository;

    @Override
    public DeleteTreeViewOutputDTO deleteTreeView(DeleteTreeViewInputDTO deleteTreeViewInputDTO) {
        String projectId = deleteTreeViewInputDTO.getProjectId();
        String datasetId = deleteTreeViewInputDTO.getDatasetId();
        String treeViewId = deleteTreeViewInputDTO.getTreeViewId();
        String userId = deleteTreeViewInputDTO.getUser().getId();

        Project project = projectRepository.findById(projectId);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException("User is not the owner of the project");

        deleteTreeView(treeViewId);

        return new DeleteTreeViewOutputDTO(projectId, datasetId, treeViewId);
    }

    @Override
    public void deleteTreeView(String treeViewId) {
        treeViewMetadataRepository.findAllByTreeViewId(treeViewId)
                .forEach(treeViewMetadata -> {
                    fileStorageRepository.delete(treeViewMetadata.getUrl());

                    // Delete the metadata
                    treeViewMetadataRepository.deleteTreeView(treeViewMetadata);
                });
    }

    @Override
    public TreeViewDTO getTreeView(String treeViewId) {
        TreeViewMetadata treeViewMetadata = treeViewMetadataRepository.findByTreeViewId(treeViewId);

        return new TreeViewDTO(treeViewMetadata);
    }
}
