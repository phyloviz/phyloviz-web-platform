package org.phyloviz.pwp.shared.repository.data.tree_view.repository;

import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewDataRepositorySpecificData;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;

public interface TreeViewDataRepository {

    GetTreeViewOutput getTreeView(TreeViewDataRepositorySpecificData treeViewDataRepositorySpecificData);

    void deleteTreeView(TreeViewDataRepositorySpecificData treeViewDataRepositorySpecificData);
}
