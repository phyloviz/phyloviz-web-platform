package org.phyloviz.pwp.shared.adapters.tree_view;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.TreeViewAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TreeViewAdapterFactory {

    private final TreeViewAdapterRegistry treeViewAdapterRegistry;

    /**
     * Returns the TreeViewAdapter for the given adapterId.
     *
     * @param adapterId the id of the adapter
     * @return the TreeViewAdapter
     */
    public TreeViewAdapter getTreeViewAdapter(TreeViewAdapterId adapterId) {
        return treeViewAdapterRegistry.getTreeViewAdapter(adapterId);
    }
}
