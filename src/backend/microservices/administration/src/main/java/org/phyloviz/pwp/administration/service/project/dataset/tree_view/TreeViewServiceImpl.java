package org.phyloviz.pwp.administration.service.project.dataset.tree_view;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.service.dtos.tree_view.TreeViewInfo;
import org.phyloviz.pwp.administration.service.dtos.tree_view.UpdateTreeViewOutput;
import org.phyloviz.pwp.shared.repository.data.registry.tree_view.TreeViewDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TreeViewNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
@RequiredArgsConstructor
public class TreeViewServiceImpl implements TreeViewService {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;
    private final TreeViewMetadataRepository treeViewMetadataRepository;

    private final TreeViewDataRepositoryFactory treeViewDataRepositoryFactory;

    @Override
    public List<TreeViewInfo> getTreeViewInfos(String datasetId) {
        return treeViewMetadataRepository.findAllByDatasetId(datasetId).stream()
                .map(TreeViewInfo::new).toList();
    }

    @Override
    public void deleteTreeView(String projectId, String datasetId, String treeViewId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();
        if (!datasetRepository.existsByProjectIdAndId(projectId, datasetId))
            throw new DatasetNotFoundException();

        TreeViewMetadata treeViewMetadata = treeViewMetadataRepository
                .findByProjectIdAndDatasetIdAndTreeViewId(projectId, datasetId, treeViewId)
                .orElseThrow(TreeViewNotFoundException::new);

        deleteTreeView(treeViewMetadata);
    }

    @Override
    public void deleteAllByProjectIdAndDatasetId(String projectId, String datasetId) {
        treeViewMetadataRepository.findAllByProjectIdAndDatasetId(projectId, datasetId)
                .forEach(this::deleteTreeView);
    }

    @Override
    public UpdateTreeViewOutput updateTreeView(String name, String projectId, String datasetId, String treeViewId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();
        if (!datasetRepository.existsByProjectIdAndId(projectId, datasetId))
            throw new DatasetNotFoundException();

        TreeViewMetadata treeViewMetadata = treeViewMetadataRepository
                .findByProjectIdAndDatasetIdAndTreeViewId(projectId, datasetId, treeViewId)
                .orElseThrow(TreeViewNotFoundException::new);

        String previousName = treeViewMetadata.getName();

        if (name == null)
            throw new InvalidArgumentException("You have to provide at least one field to update");

        if (name.isBlank())
            throw new InvalidArgumentException("Name can't be blank");

        if (!name.equals(previousName)) {
            treeViewMetadata.setName(name);
            treeViewMetadataRepository.save(treeViewMetadata);
        }

        return new UpdateTreeViewOutput(previousName, name);
    }

    private void deleteTreeView(TreeViewMetadata treeViewMetadata) {
        treeViewMetadata.getRepositorySpecificData().forEach((repositoryId, repositorySpecificData) ->
                treeViewDataRepositoryFactory.getRepository(repositoryId)
                        .deleteTreeView(repositorySpecificData)
        );

        treeViewMetadataRepository.delete(treeViewMetadata);
    }
}
