package org.phyloviz.pwp.shared.repository.metadata.tree_view.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree_view.TreeViewAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Primary
@RequiredArgsConstructor
public class TreeViewMetadataRepositoryMongo implements TreeViewMetadataRepository {

    private final TreeViewMetadataMongoRepository treeViewMetadataMongoRepository;

    @Override
    public Optional<TreeViewMetadata> findByTreeViewId(String treeViewId) {
        return treeViewMetadataMongoRepository.findAllByTreeViewId(treeViewId).stream().findAny();
    }

    @Override
    public List<TreeViewMetadata> findAllByTreeViewId(String treeViewId) {
        return treeViewMetadataMongoRepository.findAllByTreeViewId(treeViewId);
    }

    @Override
    public Optional<TreeViewMetadata> findByTreeViewIdAndAdapterId(String treeViewId, TreeViewAdapterId adapterId) {
        return treeViewMetadataMongoRepository.findByTreeViewIdAndAdapterId(
                treeViewId, adapterId.name().toLowerCase()
        );
    }

    @Override
    public Optional<TreeViewMetadata> findByProjectIdAndDatasetIdAndTreeViewIdAndAdapterId(String projectId, String datasetId, String treeViewId, TreeViewAdapterId adapterId) {
        return treeViewMetadataMongoRepository.findByProjectIdAndDatasetIdAndTreeViewIdAndAdapterId(
                projectId, datasetId, treeViewId, adapterId.name().toLowerCase()
        );
    }

    @Override
    public List<TreeViewMetadata> findAllByProjectIdAndDatasetId(String projectId, String datasetId) {
        return treeViewMetadataMongoRepository.findAllByProjectIdAndDatasetId(projectId, datasetId);
    }

    @Override
    public List<TreeViewMetadata> findAllByDatasetId(String datasetId) {
        Set<String> seenTreeViewIds = new HashSet<>();

        return treeViewMetadataMongoRepository.findAllByDatasetId(datasetId).stream()
                .filter((treeViewMetadata -> {
                    if (seenTreeViewIds.contains(treeViewMetadata.getTreeViewId())) {
                        return false;
                    }
                    seenTreeViewIds.add(treeViewMetadata.getTreeViewId());
                    return true;
                }))
                .toList();
    }

    @Override
    public void delete(TreeViewMetadata treeViewMetadata) {
        treeViewMetadataMongoRepository.delete(treeViewMetadata);
    }

    @Override
    public Boolean existsByDatasetIdAndTreeIdSource(String datasetId, String treeId) {
        return treeViewMetadataMongoRepository.existsByDatasetIdAndTreeIdSource(datasetId, treeId);
    }

    @Override
    public Boolean existsByProjectIdAndDatasetIdAndTreeViewId(String projectId, String datasetId, String treeViewId) {
        return treeViewMetadataMongoRepository.existsByProjectIdAndDatasetIdAndTreeViewId(projectId, datasetId, treeViewId);
    }
}
