package org.phyloviz.pwp.repository.data.tree.repository;

import org.phyloviz.pwp.repository.data.tree.repository.specific_data.TreeDataRepositorySpecificData;

public interface TreeDataRepository {

    String getTree(TreeDataRepositorySpecificData treeDataRepositorySpecificData);

    void deleteTree(TreeDataRepositorySpecificData treeDataRepositorySpecificData);
}
