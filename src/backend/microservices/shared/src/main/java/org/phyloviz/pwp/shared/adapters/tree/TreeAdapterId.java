package org.phyloviz.pwp.shared.adapters.tree;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree.adapter.PhyloDBTreeAdapter;
import org.phyloviz.pwp.shared.adapters.tree.adapter.S3TreeAdapter;
import org.phyloviz.pwp.shared.adapters.tree.adapter.TreeAdapter;
import org.phyloviz.pwp.shared.adapters.tree.adapter.specific_data.TreeAdapterSpecificData;
import org.phyloviz.pwp.shared.adapters.tree.adapter.specific_data.TreePhyloDBAdapterSpecificData;
import org.phyloviz.pwp.shared.adapters.tree.adapter.specific_data.TreeS3AdapterSpecificData;

@RequiredArgsConstructor
@Getter
public enum TreeAdapterId {
    PHYLODB(PhyloDBTreeAdapter.class, TreePhyloDBAdapterSpecificData.class),
    S3(S3TreeAdapter.class, TreeS3AdapterSpecificData.class);

    private final Class<? extends TreeAdapter> adapterClass;
    private final Class<? extends TreeAdapterSpecificData> adapterSpecificDataClass;
}
