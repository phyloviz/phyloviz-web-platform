package org.phyloviz.pwp.shared.adapters.tree;

import org.phyloviz.pwp.shared.adapters.tree.adapter.TreeAdapter;
import org.phyloviz.pwp.shared.adapters.tree.adapter.specific_data.TreeAdapterSpecificData;

public interface TreeAdapterRegistry {

    /**
     * Returns the TreeAdapter for the given adapterId.
     *
     * @param adapterId the id of the adapter
     * @return the TreeAdapter
     */
    TreeAdapter getTreeAdapter(TreeAdapterId adapterId);

    /**
     * Returns the TreeAdapterSpecificData class for the given adapterId.
     *
     * @param adapterId the id of the adapter
     * @return the TreeAdapterSpecificData class
     */
    Class<? extends TreeAdapterSpecificData> getTreeAdapterSpecificDataClass(TreeAdapterId adapterId);
}
