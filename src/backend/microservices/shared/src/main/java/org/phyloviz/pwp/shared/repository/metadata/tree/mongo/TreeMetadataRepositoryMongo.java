package org.phyloviz.pwp.shared.repository.metadata.tree.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree.TreeAdapterId;
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
    public Optional<TreeMetadata> findByTreeId(String treeId) {
        return treeMetadataMongoRepository.findAllByTreeId(treeId).stream().findAny();
    }

    @Override
    public List<TreeMetadata> findAllByTreeId(String treeId) {
        return treeMetadataMongoRepository.findAllByTreeId(treeId);
    }

    @Override
    public Optional<TreeMetadata> findByTreeIdAndAdapterId(String treeId, TreeAdapterId adapterId) {
        return treeMetadataMongoRepository.findByTreeIdAndAdapterId(
                treeId, adapterId.name().toLowerCase()
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
}
