package org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DistanceMatrixAdapterId {
    S3(DistanceMatrixS3AdapterSpecificData.class);

    private final Class<? extends DistanceMatrixAdapterSpecificData> adapterSpecificDataClass;
}
