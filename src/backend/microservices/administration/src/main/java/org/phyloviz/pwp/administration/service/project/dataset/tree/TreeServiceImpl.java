package org.phyloviz.pwp.administration.service.project.dataset.tree;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.service.dtos.tree.TreeInfo;
import org.phyloviz.pwp.administration.service.dtos.tree.UpdateTreeOutput;
import org.phyloviz.pwp.administration.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.shared.repository.data.registry.tree.TreeDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TreeNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;
    private final TreeMetadataRepository treeMetadataRepository;
    private final TreeViewMetadataRepository treeViewMetadataService;

    private final TreeDataRepositoryFactory treeDataRepositoryFactory;

    @Override
    public List<TreeInfo> getTreeInfos(String datasetId) {
        return treeMetadataRepository.findAllByDatasetId(datasetId).stream()
                .map(TreeInfo::new).toList();
    }

    @Override
    public void deleteTree(String projectId, String datasetId, String treeId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();
        if (!datasetRepository.existsByProjectIdAndId(projectId, datasetId))
            throw new DatasetNotFoundException();

        TreeMetadata treeMetadata = treeMetadataRepository
                .findByProjectIdAndDatasetIdAndTreeId(projectId, datasetId, treeId)
                .orElseThrow(TreeNotFoundException::new);

        if (treeViewMetadataService.existsByDatasetIdAndTreeIdSource(datasetId, treeId))
            throw new DeniedResourceDeletionException(
                    "Cannot delete tree. It is a dependency of a tree view. Delete the tree view first."
            );

        deleteTree(treeMetadata);
    }

    @Override
    public void deleteAllByProjectIdAndDatasetId(String projectId, String datasetId) {
        treeMetadataRepository.findAllByProjectIdAndDatasetId(projectId, datasetId)
                .forEach(this::deleteTree);
    }

    @Override
    public UpdateTreeOutput updateTree(String name, String projectId, String datasetId, String treeId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();
        if (!datasetRepository.existsByProjectIdAndId(projectId, datasetId))
            throw new DatasetNotFoundException();

        TreeMetadata treeMetadata = treeMetadataRepository
                .findByProjectIdAndDatasetIdAndTreeId(projectId, datasetId, treeId)
                .orElseThrow(TreeNotFoundException::new);

        String previousName = treeMetadata.getName();

        if (name == null)
            throw new InvalidArgumentException("You have to provide at least one field to update");

        if (name.isBlank())
            throw new InvalidArgumentException("Name can't be blank");

        if (!name.equals(previousName)) {
            treeMetadata.setName(name);
            treeMetadataRepository.save(treeMetadata);
        }

        return new UpdateTreeOutput(previousName, name);
    }

    private void deleteTree(TreeMetadata treeMetadata) {
        treeMetadata.getRepositorySpecificData().forEach((repositoryId, repositorySpecificData) ->
                treeDataRepositoryFactory.getRepository(repositoryId)
                        .deleteTree(repositorySpecificData)
        );

        treeMetadataRepository.delete(treeMetadata);
    }
}
