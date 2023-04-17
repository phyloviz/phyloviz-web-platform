package org.phyloviz.pwp.shared.repository.metadata.tree_view.mongo;

import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TreeViewMetadataMongoRepository extends MongoRepository<TreeViewMetadata, String> {

    /**
     * Find a tree view metadata from its id.
     *
     * @param treeViewId the id of the tree view resource
     * @return a tree view metadata
     */
    Optional<TreeViewMetadata> findByTreeViewId(String treeViewId);

    /**
     * Find all metadata representations of a tree view resource.
     *
     * @param treeViewId the id of the tree view resource
     * @return a list of tree view metadata
     */
    List<TreeViewMetadata> findAllByTreeViewId(String treeViewId);
}
