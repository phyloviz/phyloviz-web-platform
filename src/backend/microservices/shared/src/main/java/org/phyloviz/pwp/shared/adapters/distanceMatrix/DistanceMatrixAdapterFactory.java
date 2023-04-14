package org.phyloviz.pwp.shared.adapters.distanceMatrix;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DistanceMatrixAdapterFactory {

    private final S3DistanceMatrixAdapter s3DistanceMatrixAdapter;

    public DistanceMatrixAdapter getDistanceMatrixAdapter(String adapterId) {
        return switch (adapterId) {
            case "s3" -> s3DistanceMatrixAdapter;
            default -> throw new IllegalArgumentException("Unknown adapter id: " + adapterId);
        };
    }
}
