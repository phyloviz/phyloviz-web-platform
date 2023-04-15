package org.phyloviz.pwp.shared.adapters.tree;

import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterSpecificData;
import org.springframework.stereotype.Component;

@Component
public class PhyloDBTreeAdapter implements TreeAdapter {
    @Override
    public String getTree(TreeAdapterSpecificData treeAdapterSpecificData) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean isFileAdapter() {
        return false;
    }

    @Override
    public void deleteTree(TreeAdapterSpecificData treeAdapterSpecificData) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
