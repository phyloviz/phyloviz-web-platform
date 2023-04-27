package org.phyloviz.pwp.administration.service.project.dataset.tree;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.service.dtos.tree.TreeInfo;
import org.phyloviz.pwp.administration.service.dtos.tree.UpdateTreeOutput;
import org.phyloviz.pwp.administration.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.shared.repository.data.registry.tree.TreeDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TreeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

        if (!treeMetadataRepository.existsByProjectIdAndDatasetIdAndTreeId(projectId, datasetId, treeId))
            throw new TreeNotFoundException();

        if (treeViewMetadataService.existsByDatasetIdAndTreeIdSource(datasetId, treeId))
            throw new DeniedResourceDeletionException(
                    "Cannot delete tree. It is a dependency of a tree view. Delete the tree view first."
            );

        deleteTree(treeId);
    }

    @Override
    public void deleteAllByProjectIdAndDatasetId(String projectId, String datasetId) {
        treeMetadataRepository.findAllByProjectIdAndDatasetId(projectId, datasetId)
                .forEach(treeMetadata -> {
                    treeDataRepositoryFactory.getRepository(treeMetadata.getRepositoryId())
                            .deleteTree(treeMetadata.getRepositorySpecificData());

                    treeMetadataRepository.delete(treeMetadata);
                });
    }

    @Override
    public void deleteTree(String treeId) {
        treeMetadataRepository.findAllByTreeId(treeId)
                .forEach(treeMetadata -> {
                    treeDataRepositoryFactory.getRepository(treeMetadata.getRepositoryId())
                            .deleteTree(treeMetadata.getRepositorySpecificData());

                    treeMetadataRepository.delete(treeMetadata);
                });
    }

    @Override
    public UpdateTreeOutput updateTree(String name, String projectId, String datasetId, String treeId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        if (!treeMetadataRepository.existsByProjectIdAndDatasetIdAndTreeId(projectId, datasetId, treeId))
            throw new TreeNotFoundException();

        String previousName = treeMetadataRepository.findAnyByProjectIdAndDatasetIdAndTreeId(projectId, datasetId, treeId)
                .orElseThrow(TreeNotFoundException::new)
                .getName();

        if (name == null)
            throw new InvalidArgumentException("You have to provide at least one field to update");

        if (name.equals(previousName))
            throw new InvalidArgumentException("The provided name is the same as the previous one");

        treeMetadataRepository.findAllByProjectIdAndDatasetIdAndTreeId(projectId, datasetId, treeId)
                .forEach(treeMetadata -> {
                    if (!name.isBlank())
                        treeMetadata.setName(name);

                    treeMetadataRepository.save(treeMetadata);
                });

        return new UpdateTreeOutput(previousName, name);
    }
}
