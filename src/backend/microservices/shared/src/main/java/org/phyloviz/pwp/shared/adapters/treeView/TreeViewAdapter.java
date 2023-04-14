package org.phyloviz.pwp.shared.adapters.treeView;

import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewAdapterSpecificData;

public interface TreeViewAdapter {

    AdapterGetTreeViewDTO getTreeView(TreeViewAdapterSpecificData treeViewAdapterSpecificData);
}
