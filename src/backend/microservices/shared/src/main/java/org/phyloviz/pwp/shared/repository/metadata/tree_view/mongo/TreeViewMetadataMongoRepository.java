package org.phyloviz.pwp.shared.repository.metadata.tree_view.mongo;

import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.repository.metadata.typing_dataset.documents.TypingDatasetMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TreeViewMetadataMongoRepository extends MongoRepository<TreeViewMetadata, String> {

    /**
     * Find a tree view metadata from its resource id.
     *
     * @param resourceId the resource id of the tree view
     * @return a tree view metadata
     */
    TreeViewMetadata findByResourceId(String resourceId);
}
