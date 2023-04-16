package org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.source;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DistanceMatrixSourceType {
    FUNCTION(DistanceMatrixSourceFunction.class);

    private final Class<? extends DistanceMatrixSource> sourceClass;
}
