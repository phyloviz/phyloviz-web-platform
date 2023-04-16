package org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TreeViewAdapterId {
    PHYLODB(TreeViewPhyloDBAdapterSpecificData.class),
    S3(TreeViewS3AdapterSpecificData.class);

    private final Class<? extends TreeViewAdapterSpecificData> adapterSpecificDataClass;
}
