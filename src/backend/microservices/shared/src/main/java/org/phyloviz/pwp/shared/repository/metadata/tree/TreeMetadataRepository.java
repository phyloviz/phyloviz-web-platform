package org.phyloviz.pwp.shared.repository.metadata.tree;

import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreeMetadataRepository {

    /**
     * Deletes a tree metadata.
     *
     * @param treeMetadata the tree metadata to delete
     */
    void delete(TreeMetadata treeMetadata);

    /**
     * Find a tree metadata from its id.
     *
     * @param treeId the id of the tree resource
     * @return a tree metadata
     */
    TreeMetadata findByTreeId(String treeId);

    /**
     * Find all metadata representations of a tree resource.
     *
     * @param treeId the id of the tree resource
     * @return a list of tree metadata
     */
    List<TreeMetadata> findAllByTreeId(String treeId);
}
