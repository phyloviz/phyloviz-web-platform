package org.phyloviz.pwp.administration.service.project.dataset.tree_view;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.service.dtos.distance_matrix.UpdateDistanceMatrixOutput;
import org.phyloviz.pwp.administration.service.dtos.tree_view.UpdateTreeViewOutput;
import org.phyloviz.pwp.shared.adapters.tree_view.TreeViewAdapterFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.TreeViewMetadataRepository;
import org.phyloviz.pwp.administration.service.dtos.tree_view.TreeViewInfo;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.DistanceMatrixNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TreeViewNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeViewServiceImpl implements TreeViewService {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;
    private final TreeViewMetadataRepository treeViewMetadataRepository;

    private final TreeViewAdapterFactory treeViewAdapterFactory;

    @Override
    public List<TreeViewInfo> getTreeViewInfos(String datasetId) {
        return treeViewMetadataRepository.findAllByDatasetId(datasetId).stream()
                .map(TreeViewInfo::new).toList();
    }

    @Override
    public void deleteTreeView(String projectId, String datasetId, String treeViewId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();
        if (!datasetRepository.existsByProjectIdAndId(datasetId, projectId))
            throw new DatasetNotFoundException();

        if (!treeViewMetadataRepository.existsByProjectIdAndDatasetIdAndTreeViewId(projectId, datasetId, treeViewId)) {
            throw new TreeViewNotFoundException();
        }

        deleteTreeView(treeViewId);
    }

    @Override
    public void deleteAllByProjectIdAndDatasetId(String projectId, String datasetId) {
        treeViewMetadataRepository.findAllByProjectIdAndDatasetId(projectId, datasetId)
                .forEach(treeViewMetadata -> {
                    treeViewAdapterFactory.getTreeViewAdapter(treeViewMetadata.getAdapterId())
                            .deleteTreeView(treeViewMetadata.getAdapterSpecificData());

                    treeViewMetadataRepository.delete(treeViewMetadata);
                });
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
    public UpdateTreeViewOutput updateTreeView(String name, String projectId, String datasetId, String treeViewId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        if (!treeViewMetadataRepository.existsByProjectIdAndDatasetIdAndTreeViewId(projectId, datasetId, treeViewId))
            throw new TreeViewNotFoundException();

        String previousName = treeViewMetadataRepository.findAnyByProjectIdAndDatasetIdAndTreeViewId(projectId, datasetId, treeViewId)
                .orElseThrow(TreeViewNotFoundException::new)
                .getName();

        if (name == null)
            throw new InvalidArgumentException("You have to provide at least one field to update");

        if (name.equals(previousName))
            throw new InvalidArgumentException("The provided name is the same as the previous one");

        treeViewMetadataRepository.findAllByProjectIdAndDatasetIdAndTreeViewId(projectId, datasetId, treeViewId)
                .forEach(treeViewMetadata -> {
                    if (!name.isBlank())
                        treeViewMetadata.setName(name);

                    treeViewMetadataRepository.save(treeViewMetadata);
                });

        return new UpdateTreeViewOutput(previousName, name);
    }
}
