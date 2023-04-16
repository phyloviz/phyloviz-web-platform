package org.phyloviz.pwp.shared.service.adapters.distanceMatrix;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData.DistanceMatrixAdapterId;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DistanceMatrixAdapterFactory {

    private final S3DistanceMatrixAdapter s3DistanceMatrixAdapter;

    public DistanceMatrixAdapter getDistanceMatrixAdapter(DistanceMatrixAdapterId adapterId) {
        return switch (adapterId) {
            case S3 -> s3DistanceMatrixAdapter;
        };
    }
}
