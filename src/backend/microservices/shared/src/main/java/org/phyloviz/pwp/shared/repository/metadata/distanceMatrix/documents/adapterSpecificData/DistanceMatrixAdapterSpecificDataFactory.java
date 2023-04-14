package org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DistanceMatrixAdapterSpecificDataFactory {

    public Class<? extends DistanceMatrixAdapterSpecificData> getClass(String adapterId) {
        return switch (adapterId) {
            case "s3" -> DistanceMatrixS3AdapterSpecificData.class;
            default -> throw new IllegalArgumentException("Unknown adapter id: " + adapterId);
        };
    }
}
