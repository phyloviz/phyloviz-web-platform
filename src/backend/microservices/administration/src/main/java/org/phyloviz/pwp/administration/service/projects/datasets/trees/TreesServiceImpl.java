package org.phyloviz.pwp.administration.service.projects.datasets.trees;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.repository.data.FileStorageRepository;
import org.phyloviz.pwp.administration.service.dtos.trees.TreeDTO;
import org.phyloviz.pwp.administration.service.dtos.trees.deleteTree.DeleteTreeInputDTO;
import org.phyloviz.pwp.administration.service.dtos.trees.deleteTree.DeleteTreeOutputDTO;
import org.phyloviz.pwp.administration.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.treeView.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TreeNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link TreesService} interface.
 */
@Service
@RequiredArgsConstructor
public class TreesServiceImpl implements TreesService {

    private final TreeMetadataRepository treeMetadataRepository;
    private final TreeViewMetadataRepository treeViewMetadataRepository;
    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;
    private final FileStorageRepository fileStorageRepository;

    @Override
    public DeleteTreeOutputDTO deleteTree(DeleteTreeInputDTO deleteTreeInputDTO) {
        String projectId = deleteTreeInputDTO.getProjectId();
        String datasetId = deleteTreeInputDTO.getDatasetId();
        String treeId = deleteTreeInputDTO.getTreeId();
        String userId = deleteTreeInputDTO.getUser().getId();

        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException();

        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(DatasetNotFoundException::new);

        dataset.getTreeViewIds().forEach(treeViewId -> {
            TreeViewMetadata treeViewMetadata = treeViewMetadataRepository.findByTreeViewId(treeViewId).orElse(null);

            if (treeViewMetadata != null && treeViewMetadata.getSource().getTreeId().equals(treeId)) {
                throw new DeniedResourceDeletionException(
                        "Cannot delete tree. It is a dependency of a tree view (treeViewId = " + treeViewId + "). " +
                                "Delete the tree view first."
                );
            }
        });

        treeMetadataRepository.findByTreeId(treeId).orElseThrow(TreeNotFoundException::new);

        deleteTree(treeId);

        dataset.getTreeIds().remove(treeId);
        datasetRepository.save(dataset);

        return new DeleteTreeOutputDTO(projectId, datasetId, treeId);
    }

    @Override
    public void deleteTree(String treeId) {
        treeMetadataRepository.findAllByTreeId(treeId)
                .forEach(treeMetadata -> {
                    fileStorageRepository.delete(treeMetadata.getUrl());
                    treeMetadataRepository.delete(treeMetadata);
                });
    }

    @Override
    public TreeDTO getTree(String treeId) {
        TreeMetadata treeMetadata = treeMetadataRepository
                .findByTreeId(treeId)
                .orElseThrow(TreeNotFoundException::new);

        return new TreeDTO(treeMetadata);
    }
}
