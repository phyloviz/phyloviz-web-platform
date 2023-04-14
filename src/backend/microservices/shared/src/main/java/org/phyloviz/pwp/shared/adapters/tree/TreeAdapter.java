package org.phyloviz.pwp.shared.adapters.tree;

import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterSpecificData;

public interface TreeAdapter {

    String getTree(TreeAdapterSpecificData treeAdapterSpecificData);

    boolean isFileAdapter();
}
