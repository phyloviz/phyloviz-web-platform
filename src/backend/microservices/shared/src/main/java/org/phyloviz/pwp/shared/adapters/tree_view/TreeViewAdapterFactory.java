package org.phyloviz.pwp.shared.adapters.tree_view;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.TreeViewAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TreeViewAdapterFactory {

    private final TreeViewAdapterRegistry treeViewAdapterRegistry;

    public TreeViewAdapter getTreeViewAdapter(TreeViewAdapterId adapterId) {
        return treeViewAdapterRegistry.getTreeViewAdapter(adapterId);
    }
}
