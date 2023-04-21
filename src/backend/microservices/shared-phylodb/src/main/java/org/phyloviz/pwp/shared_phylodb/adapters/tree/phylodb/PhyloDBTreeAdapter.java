package org.phyloviz.pwp.shared_phylodb.adapters.tree.phylodb;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree.adapter.TreeAdapter;
import org.phyloviz.pwp.shared.adapters.tree.adapter.specific_data.TreeAdapterSpecificData;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
