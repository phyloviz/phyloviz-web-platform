package org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.source;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DistanceMatrixSourceFactory {

    public Class<? extends DistanceMatrixSource> getClass(String sourceType) {
        return switch (sourceType) {
            case "function" -> DistanceMatrixSourceFunction.class;
            default -> throw new IllegalArgumentException("Unknown source type: " + sourceType);
        };
    }
}
