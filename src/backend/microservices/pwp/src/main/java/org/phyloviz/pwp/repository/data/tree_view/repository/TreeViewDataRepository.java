package org.phyloviz.pwp.repository.data.tree_view.repository;

import org.phyloviz.pwp.repository.data.tree_view.repository.specific_data.TreeViewDataRepositorySpecificData;
import org.phyloviz.pwp.service.dtos.tree_view.GetTreeViewOutput;
import org.phyloviz.pwp.service.dtos.tree_view.Node;

import java.util.List;

public interface TreeViewDataRepository {

    GetTreeViewOutput getTreeView(TreeViewDataRepositorySpecificData treeViewDataRepositorySpecificData);

    void saveTreeView(TreeViewDataRepositorySpecificData treeViewDataRepositorySpecificData, List<Node> nodes);

    void deleteTreeView(TreeViewDataRepositorySpecificData treeViewDataRepositorySpecificData);
}
