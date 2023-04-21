package org.phyloviz.pwp.shared.adapters.tree;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree.adapter.TreeAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TreeAdapterFactory {

    private final TreeAdapterRegistry treeAdapterRegistry;

    /**
     * Returns the TreeAdapter for the given adapterId.
     *
     * @param adapterId the id of the adapter
     * @return the TreeAdapter
     */
    public TreeAdapter getTreeAdapter(TreeAdapterId adapterId) {
        return treeAdapterRegistry.getTreeAdapter(adapterId);
    }
}
