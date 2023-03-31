package org.phyloviz.pwp.shared.repository.metadata.tree.mongo;

import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreeMetadataMongoRepository extends MongoRepository<TreeMetadata, String> {

    /**
     * Find a tree metadata from its id.
     *
     * @param resourceId the id of the tree resource
     * @return an inference tree metadata
     */
    TreeMetadata findByTreeId(String resourceId);

    /**
     * Find all metadata representations of a tree resource.
     *
     * @param treeId the id of the tree resource
     * @return a list of tree metadata
     */
    List<TreeMetadata> findAllByTreeId(String treeId);
}
