package org.phyloviz.pwp.administration.http.models.distanceMatrices;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.distance_matrices.DistanceMatrixSourceFunctionDTO;

@Data
public class DistanceMatrixSourceFunctionOutputModel implements DistanceMatrixSourceOutputModel {
    private String function;

    public DistanceMatrixSourceFunctionOutputModel(DistanceMatrixSourceFunctionDTO distanceMatrixSourceFunctionDTO) {
        this.function = distanceMatrixSourceFunctionDTO.getFunction();
    }
}
