package org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.service.adapters.distanceMatrix.DistanceMatrixAdapter;
import org.phyloviz.pwp.shared.service.adapters.isolateData.IsolateDataAdapter;
import org.phyloviz.pwp.shared.service.adapters.isolateData.S3IsolateDataAdapter;

@RequiredArgsConstructor
@Getter
public enum IsolateDataAdapterId {
    S3(IsolateDataS3AdapterSpecificData.class, S3IsolateDataAdapter.class);

    private final Class<? extends IsolateDataAdapterSpecificData> adapterSpecificDataClass;
    private final Class<? extends IsolateDataAdapter> adapterClass;
}
