package org.phyloviz.pwp.shared.service.dtos.distance_matrix;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.source.DistanceMatrixSourceFunction;

@Data
public class DistanceMatrixSourceFunctionInfo implements DistanceMatrixSourceInfo {
    private final String function;

    public DistanceMatrixSourceFunctionInfo(DistanceMatrixSourceFunction distanceMatrixSourceFunction) {
        this.function = distanceMatrixSourceFunction.getFunction();
    }
}
