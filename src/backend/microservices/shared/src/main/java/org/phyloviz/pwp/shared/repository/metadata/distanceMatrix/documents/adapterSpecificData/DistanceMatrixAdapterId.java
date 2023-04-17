package org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.service.adapters.distanceMatrix.DistanceMatrixAdapter;
import org.phyloviz.pwp.shared.service.adapters.distanceMatrix.S3DistanceMatrixAdapter;

@RequiredArgsConstructor
@Getter
public enum DistanceMatrixAdapterId {
    S3(DistanceMatrixS3AdapterSpecificData.class, S3DistanceMatrixAdapter.class);

    private final Class<? extends DistanceMatrixAdapterSpecificData> adapterSpecificDataClass;
    private final Class<? extends DistanceMatrixAdapter> adapterClass;
}
