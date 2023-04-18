package org.phyloviz.pwp.shared.adapters.tree_view;

import org.phyloviz.pwp.shared.adapters.tree_view.adapter.TreeViewAdapter;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.specific_data.TreeViewAdapterSpecificData;


public interface TreeViewAdapterRegistry {

    TreeViewAdapter getTreeViewAdapter(TreeViewAdapterId adapterId);

    Class<? extends TreeViewAdapterSpecificData> getTreeViewAdapterSpecificDataClass(TreeViewAdapterId adapterId);
}
