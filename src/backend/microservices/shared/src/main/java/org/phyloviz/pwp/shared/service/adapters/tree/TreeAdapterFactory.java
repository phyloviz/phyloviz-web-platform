package org.phyloviz.pwp.shared.service.adapters.tree;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterId;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TreeAdapterFactory {

    private final TreeAdapterRegistry treeAdapterRegistry;

    public TreeAdapter getTreeAdapter(TreeAdapterId adapterId) {
        return treeAdapterRegistry.getTreeAdapter(adapterId);
    }
}
