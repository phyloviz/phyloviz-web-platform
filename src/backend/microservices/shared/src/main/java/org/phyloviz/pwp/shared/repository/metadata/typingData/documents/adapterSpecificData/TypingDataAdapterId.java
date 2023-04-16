package org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TypingDataAdapterId {
    S3(TypingDataS3AdapterSpecificData.class),
    PHYLODB(TypingDataPhyloDBAdapterSpecificData.class);

    private final Class<? extends TypingDataAdapterSpecificData> adapterSpecificDataClass;
}
