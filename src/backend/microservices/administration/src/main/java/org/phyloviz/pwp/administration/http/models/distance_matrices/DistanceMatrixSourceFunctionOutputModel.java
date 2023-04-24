package org.phyloviz.pwp.administration.http.models.distance_matrices;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.distance_matrix.DistanceMatrixSourceFunctionInfo;

@Data
public class DistanceMatrixSourceFunctionOutputModel implements DistanceMatrixSourceOutputModel {
    private String function;

    public DistanceMatrixSourceFunctionOutputModel(DistanceMatrixSourceFunctionInfo distanceMatrixSourceFunctionInfo) {
        this.function = distanceMatrixSourceFunctionInfo.getFunction();
    }
}
