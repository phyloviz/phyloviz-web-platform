package org.phyloviz.pwp.shared.repository.data.tree.repository;

import org.phyloviz.pwp.shared.repository.data.tree.repository.specific_data.TreeDataRepositorySpecificData;

public interface TreeDataRepository {

    String getTree(TreeDataRepositorySpecificData treeDataRepositorySpecificData);

    boolean isFileRepository();

    void deleteTree(TreeDataRepositorySpecificData treeDataRepositorySpecificData);
}
