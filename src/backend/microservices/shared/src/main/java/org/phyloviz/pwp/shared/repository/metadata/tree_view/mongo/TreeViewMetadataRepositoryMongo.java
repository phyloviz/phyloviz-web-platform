package org.phyloviz.pwp.shared.repository.metadata.tree_view.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.tree_view.TreeViewDataRepositoryId;
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
    public Optional<TreeViewMetadata> findByProjectIdAndDatasetIdAndTreeViewId(String projectId, String datasetId, String treeViewId) {
        return treeViewMetadataMongoRepository.findByProjectIdAndDatasetIdAndTreeViewId(projectId, datasetId, treeViewId);
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
    public TreeViewMetadata save(TreeViewMetadata treeViewMetadata) {
        return treeViewMetadataMongoRepository.save(treeViewMetadata);
    }

    @Override
    public boolean existsByDatasetIdAndTreeIdSource(String datasetId, String treeId) {
        return treeViewMetadataMongoRepository.existsByDatasetIdAndTreeIdSource(datasetId, treeId);
    }
}
