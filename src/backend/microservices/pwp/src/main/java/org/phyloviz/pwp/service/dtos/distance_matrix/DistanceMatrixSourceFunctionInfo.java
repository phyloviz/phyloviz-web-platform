package org.phyloviz.pwp.service.dtos.distance_matrix;

import lombok.Data;
import org.phyloviz.pwp.repository.metadata.distance_matrix.documents.source.DistanceMatrixSourceFunction;

@Data
public class DistanceMatrixSourceFunctionInfo implements DistanceMatrixSourceInfo {
    private final String function;

    public DistanceMatrixSourceFunctionInfo(DistanceMatrixSourceFunction distanceMatrixSourceFunction) {
        this.function = distanceMatrixSourceFunction.getFunction();
    }
}
