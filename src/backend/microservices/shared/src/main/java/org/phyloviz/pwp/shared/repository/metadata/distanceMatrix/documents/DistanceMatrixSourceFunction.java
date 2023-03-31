package org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents;

import lombok.Data;

@Data
public class DistanceMatrixSourceFunction implements DistanceMatrixSource {
    private final String function;
}
