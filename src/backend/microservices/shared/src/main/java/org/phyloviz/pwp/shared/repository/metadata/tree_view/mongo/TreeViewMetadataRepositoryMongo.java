package org.phyloviz.pwp.shared.repository.metadata.tree_view.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TreeViewMetadataRepositoryMongo implements TreeViewMetadataRepository {

    private final TreeViewMetadataMongoRepository treeViewMetadataMongoRepository;

    @Override
    public void deleteTreeView(TreeViewMetadata treeViewMetadata) {
        treeViewMetadataMongoRepository.delete(treeViewMetadata);
    }

    @Override
    public TreeViewMetadata findByResourceId(String resourceId) {
        return treeViewMetadataMongoRepository.findByResourceId(resourceId);
    }
}
