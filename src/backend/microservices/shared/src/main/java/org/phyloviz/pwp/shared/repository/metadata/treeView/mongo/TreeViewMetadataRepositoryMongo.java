package org.phyloviz.pwp.shared.repository.metadata.treeView.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.treeView.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewMetadata;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class TreeViewMetadataRepositoryMongo implements TreeViewMetadataRepository {

    private final TreeViewMetadataMongoRepository treeViewMetadataMongoRepository;

    @Override
    public void deleteTreeView(TreeViewMetadata treeViewMetadata) {
        treeViewMetadataMongoRepository.delete(treeViewMetadata);
    }

    @Override
    public Optional<TreeViewMetadata> findByTreeViewId(String treeViewId) {
        return treeViewMetadataMongoRepository.findByTreeViewId(treeViewId);
    }

    @Override
    public List<TreeViewMetadata> findAllByTreeViewId(String treeViewId) {
        return treeViewMetadataMongoRepository.findAllByTreeViewId(treeViewId);
    }
}
