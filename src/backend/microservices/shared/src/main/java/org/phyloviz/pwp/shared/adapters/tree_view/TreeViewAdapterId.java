package org.phyloviz.pwp.shared.adapters.tree_view;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.PhyloDBTreeViewAdapter;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.S3TreeViewAdapter;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.TreeViewAdapter;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.specific_data.TreeViewAdapterSpecificData;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.specific_data.TreeViewPhyloDBAdapterSpecificData;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.specific_data.TreeViewS3AdapterSpecificData;

@RequiredArgsConstructor
@Getter
public enum TreeViewAdapterId {
    PHYLODB(PhyloDBTreeViewAdapter.class, TreeViewPhyloDBAdapterSpecificData.class),
    S3(S3TreeViewAdapter.class, TreeViewS3AdapterSpecificData.class);

    private final Class<? extends TreeViewAdapter> adapterClass;
    private final Class<? extends TreeViewAdapterSpecificData> adapterSpecificDataClass;
}
