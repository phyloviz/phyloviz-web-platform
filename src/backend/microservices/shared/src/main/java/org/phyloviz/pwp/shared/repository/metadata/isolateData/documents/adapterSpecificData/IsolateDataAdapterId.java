package org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum IsolateDataAdapterId {
    S3(IsolateDataS3AdapterSpecificData.class);

    private final Class<? extends IsolateDataAdapterSpecificData> adapterSpecificDataClass;
}
