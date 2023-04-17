package org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.service.adapters.distanceMatrix.DistanceMatrixAdapter;
import org.phyloviz.pwp.shared.service.adapters.treeView.PhyloDBTreeViewAdapter;
import org.phyloviz.pwp.shared.service.adapters.treeView.S3TreeViewAdapter;
import org.phyloviz.pwp.shared.service.adapters.treeView.TreeViewAdapter;

@RequiredArgsConstructor
@Getter
public enum TreeViewAdapterId {
    PHYLODB(TreeViewPhyloDBAdapterSpecificData.class, PhyloDBTreeViewAdapter.class),
    S3(TreeViewS3AdapterSpecificData.class, S3TreeViewAdapter.class);

    private final Class<? extends TreeViewAdapterSpecificData> adapterSpecificDataClass;
    private final Class<? extends TreeViewAdapter> adapterClass;
}
