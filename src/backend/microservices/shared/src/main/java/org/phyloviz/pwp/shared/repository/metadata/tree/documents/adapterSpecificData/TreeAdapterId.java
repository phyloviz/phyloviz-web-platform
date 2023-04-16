package org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TreeAdapterId {
    PHYLODB(TreePhyloDBAdapterSpecificData.class),
    S3(TreeS3AdapterSpecificData.class);

    private final Class<? extends TreeAdapterSpecificData> adapterSpecificDataClass;
}
