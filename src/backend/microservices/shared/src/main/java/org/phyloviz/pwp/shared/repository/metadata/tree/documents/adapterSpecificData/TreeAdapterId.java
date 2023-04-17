package org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.service.adapters.distanceMatrix.DistanceMatrixAdapter;
import org.phyloviz.pwp.shared.service.adapters.tree.PhyloDBTreeAdapter;
import org.phyloviz.pwp.shared.service.adapters.tree.S3TreeAdapter;
import org.phyloviz.pwp.shared.service.adapters.tree.TreeAdapter;

@RequiredArgsConstructor
@Getter
public enum TreeAdapterId {
    PHYLODB(TreePhyloDBAdapterSpecificData.class, PhyloDBTreeAdapter.class),
    S3(TreeS3AdapterSpecificData.class, S3TreeAdapter.class);

    private final Class<? extends TreeAdapterSpecificData> adapterSpecificDataClass;
    private final Class<? extends TreeAdapter> adapterClass;
}
