package org.phyloviz.pwp.shared.adapters.tree_view.adapter;

import org.phyloviz.pwp.shared.adapters.tree_view.adapter.specific_data.TreeViewAdapterSpecificData;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;

public interface TreeViewAdapter {

    GetTreeViewOutput getTreeView(TreeViewAdapterSpecificData treeViewAdapterSpecificData);

    void deleteTreeView(TreeViewAdapterSpecificData treeViewAdapterSpecificData);
}
