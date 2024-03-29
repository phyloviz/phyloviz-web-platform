package org.phyloviz.pwp.repository.metadata.tree.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.repository.metadata.tree.documents.TreeMetadata;
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
    public Optional<TreeMetadata> findByProjectIdAndDatasetIdAndTreeId(String projectId, String datasetId, String treeId) {
        return treeMetadataMongoRepository.findByProjectIdAndDatasetIdAndTreeId(projectId, datasetId, treeId);
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
    public List<TreeMetadata> findAllByProjectIdAndDatasetId(String projectId, String datasetId) {
        return treeMetadataMongoRepository.findAllByProjectIdAndDatasetId(projectId, datasetId);
    }

    @Override
    public boolean existsByDatasetIdAndDistanceMatrixIdSource(String datasetId, String distanceMatrixId) {
        return !treeMetadataMongoRepository.findByDatasetIdAndDistanceMatrixIdSource(datasetId, distanceMatrixId).isEmpty();
    }
}
