package org.phyloviz.pwp.shared.service.adapters.treeView;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewAdapterId;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TreeViewAdapterFactory {

    private final TreeViewAdapterRegistry treeViewAdapterRegistry;

    public TreeViewAdapter getTreeViewAdapter(TreeViewAdapterId adapterId) {
        return treeViewAdapterRegistry.getTreeViewAdapter(adapterId);
    }
}
