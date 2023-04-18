package org.phyloviz.pwp.shared.adapters.tree;

import org.phyloviz.pwp.shared.adapters.tree.adapter.TreeAdapter;
import org.phyloviz.pwp.shared.adapters.tree.adapter.specific_data.TreeAdapterSpecificData;


public interface TreeAdapterRegistry {

    TreeAdapter getTreeAdapter(TreeAdapterId adapterId);

    Class<? extends TreeAdapterSpecificData> getTreeAdapterSpecificDataClass(TreeAdapterId adapterId);
}
