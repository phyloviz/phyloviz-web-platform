package org.phyloviz.pwp.shared.repository.metadata.tree.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.tree.TreeDataRepositoryId;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Primary
@RequiredArgsConstructor
public class TreeMetadataRepositoryMongo implements TreeMetadataRepository {

    private final TreeMetadataMongoRepository treeMetadataMongoRepository;

    @Override
    public Optional<TreeMetadata> findAnyByProjectIdAndDatasetIdAndTreeId(String projectId, String datasetId, String treeId) {
        return treeMetadataMongoRepository.findFirstByProjectIdAndDatasetIdAndTreeId(projectId, datasetId, treeId);
    }

    @Override
    public List<TreeMetadata> findAllByProjectIdAndDatasetIdAndTreeId(String projectId, String datasetId, String treeId) {
        return treeMetadataMongoRepository.findAllByProjectIdAndDatasetIdAndTreeId(projectId, datasetId, treeId);
    }

    @Override
    public List<TreeMetadata> findAllByTreeId(String treeId) {
        return treeMetadataMongoRepository.findAllByTreeId(treeId);
    }

    @Override
    public Optional<TreeMetadata> findByTreeIdAndRepositoryId(String treeId, TreeDataRepositoryId repositoryId) {
        return treeMetadataMongoRepository.findByTreeIdAndRepositoryId(
                treeId, repositoryId.name().toLowerCase()
        );
    }

    @Override
    public List<TreeMetadata> findAllByDatasetId(String datasetId) {
        Set<String> seenTreeIds = new HashSet<>();

        return treeMetadataMongoRepository.findAllByDatasetId(datasetId).stream()
                .filter((treeMetadata -> {
                    if (seenTreeIds.contains(treeMetadata.getTreeId())) {
                        return false;
                    }
                    seenTreeIds.add(treeMetadata.getTreeId());
                    return true;
                }))
                .toList();
    }

    @Override
    public void delete(TreeMetadata treeMetadata) {
        treeMetadataMongoRepository.delete(treeMetadata);
    }

    @Override
    public TreeMetadata save(TreeMetadata treeMetadata) {
        return treeMetadataMongoRepository.save(treeMetadata);
    }

    @Override
    public Optional<TreeMetadata> findByProjectIdAndDatasetIdAndTreeViewIdAndRepositoryId(String projectId, String datasetId, String treeViewId, TreeDataRepositoryId repositoryId) {
        return treeMetadataMongoRepository.findByProjectIdAndDatasetIdAndTreeViewIdAndRepositoryId(
                projectId, datasetId, treeViewId, repositoryId.name().toLowerCase()
        );
    }

    @Override
    public List<TreeMetadata> findAllByProjectIdAndDatasetId(String projectId, String datasetId) {
        return treeMetadataMongoRepository.findAllByProjectIdAndDatasetId(projectId, datasetId);
    }

    @Override
    public boolean existsByDatasetIdAndDistanceMatrixIdSource(String datasetId, String distanceMatrixId) {
        return treeMetadataMongoRepository.existsByDatasetIdAndDistanceMatrixIdSource(datasetId, distanceMatrixId);
    }

    @Override
    public boolean existsByProjectIdAndDatasetIdAndTreeId(String projectId, String datasetId, String treeId) {
        return treeMetadataMongoRepository.existsByProjectIdAndDatasetIdAndTreeId(projectId, datasetId, treeId);
    }
}
