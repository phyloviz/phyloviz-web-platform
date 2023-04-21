package org.phyloviz.pwp.shared.adapters.tree_view;

import org.phyloviz.pwp.shared.adapters.tree_view.adapter.TreeViewAdapter;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.specific_data.TreeViewAdapterSpecificData;

public interface TreeViewAdapterRegistry {

    /**
     * Returns the TreeViewAdapter for the given adapterId.
     *
     * @param adapterId the id of the adapter
     * @return the TreeViewAdapter
     */
    TreeViewAdapter getTreeViewAdapter(TreeViewAdapterId adapterId);

    /**
     * Returns the TreeViewAdapterSpecificData class for the given adapterId.
     *
     * @param adapterId the id of the adapter
     * @return the TreeViewAdapterSpecificData class
     */
    Class<? extends TreeViewAdapterSpecificData> getTreeViewAdapterSpecificDataClass(TreeViewAdapterId adapterId);
}
