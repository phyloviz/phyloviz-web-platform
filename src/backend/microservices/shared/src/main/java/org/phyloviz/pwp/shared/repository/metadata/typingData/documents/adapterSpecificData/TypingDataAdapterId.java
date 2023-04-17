package org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.service.adapters.distanceMatrix.DistanceMatrixAdapter;
import org.phyloviz.pwp.shared.service.adapters.typingData.PhyloDBTypingDataAdapter;
import org.phyloviz.pwp.shared.service.adapters.typingData.S3TypingDataAdapter;
import org.phyloviz.pwp.shared.service.adapters.typingData.TypingDataAdapter;

@RequiredArgsConstructor
@Getter
public enum TypingDataAdapterId {
    S3(TypingDataS3AdapterSpecificData.class, S3TypingDataAdapter.class),
    PHYLODB(TypingDataPhyloDBAdapterSpecificData.class, PhyloDBTypingDataAdapter.class);

    private final Class<? extends TypingDataAdapterSpecificData> adapterSpecificDataClass;
    private final Class<? extends TypingDataAdapter> adapterClass;
}
