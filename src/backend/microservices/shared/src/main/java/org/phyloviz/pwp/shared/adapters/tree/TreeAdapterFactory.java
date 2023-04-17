package org.phyloviz.pwp.shared.adapters.tree;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree.adapter.TreeAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TreeAdapterFactory {

    private final TreeAdapterRegistry treeAdapterRegistry;

    public TreeAdapter getTreeAdapter(TreeAdapterId adapterId) {
        return treeAdapterRegistry.getTreeAdapter(adapterId);
    }
}
