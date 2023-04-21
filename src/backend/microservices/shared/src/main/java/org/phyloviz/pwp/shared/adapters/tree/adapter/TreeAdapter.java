package org.phyloviz.pwp.shared.adapters.tree.adapter;

import org.phyloviz.pwp.shared.adapters.tree.adapter.specific_data.TreeAdapterSpecificData;

public interface TreeAdapter {

    String getTree(TreeAdapterSpecificData treeAdapterSpecificData);

    boolean isFileAdapter();

    void deleteTree(TreeAdapterSpecificData treeAdapterSpecificData);
}
