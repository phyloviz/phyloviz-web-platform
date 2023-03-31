package org.phyloviz.pwp.shared.repository.metadata.tree.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class TreeMetadataRepositoryMongo implements TreeMetadataRepository {

    private final TreeMetadataMongoRepository treeMetadataMongoRepository;

    @Override
    public void delete(TreeMetadata treeMetadata) {
        treeMetadataMongoRepository.delete(treeMetadata);
    }

    @Override
    public TreeMetadata findByTreeId(String treeId) {
        return treeMetadataMongoRepository.findByTreeId(treeId);
    }

    @Override
    public List<TreeMetadata> findAllByTreeId(String treeId) {
        return treeMetadataMongoRepository.findAllByTreeId(treeId);
    }
}
