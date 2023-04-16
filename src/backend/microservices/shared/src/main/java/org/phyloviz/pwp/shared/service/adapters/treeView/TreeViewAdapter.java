package org.phyloviz.pwp.shared.service.adapters.treeView;

import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewAdapterSpecificData;
import org.phyloviz.pwp.shared.service.dtos.treeView.GetTreeViewOutput;

public interface TreeViewAdapter {

    GetTreeViewOutput getTreeView(TreeViewAdapterSpecificData treeViewAdapterSpecificData);

    void deleteTreeView(TreeViewAdapterSpecificData treeViewAdapterSpecificData);
}
