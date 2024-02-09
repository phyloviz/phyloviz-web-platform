package org.phyloviz.pwp.http.models.distance_matrices;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.distance_matrix.DistanceMatrixSourceFunctionInfo;

@Data
public class DistanceMatrixSourceFunctionOutputModel implements DistanceMatrixSourceOutputModel {
    private String function;

    public DistanceMatrixSourceFunctionOutputModel(DistanceMatrixSourceFunctionInfo distanceMatrixSourceFunctionInfo) {
        this.function = distanceMatrixSourceFunctionInfo.getFunction();
    }
}
