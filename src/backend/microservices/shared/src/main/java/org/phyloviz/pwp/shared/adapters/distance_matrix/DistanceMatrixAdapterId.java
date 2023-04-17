package org.phyloviz.pwp.shared.adapters.distance_matrix;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.DistanceMatrixAdapter;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.S3DistanceMatrixAdapter;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.specific_data.DistanceMatrixAdapterSpecificData;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.specific_data.DistanceMatrixS3AdapterSpecificData;

@RequiredArgsConstructor
@Getter
public enum DistanceMatrixAdapterId {
    S3(S3DistanceMatrixAdapter.class, DistanceMatrixS3AdapterSpecificData.class);

    private final Class<? extends DistanceMatrixAdapter> adapterClass;
    private final Class<? extends DistanceMatrixAdapterSpecificData> adapterSpecificDataClass;
}
