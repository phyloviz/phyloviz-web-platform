package org.phyloviz.pwp.administration.service.dtos.distance_matrices;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.source.DistanceMatrixSourceFunction;

@Data
public class DistanceMatrixSourceFunctionDTO implements DistanceMatrixSourceDTO {
    private final String function;

    public DistanceMatrixSourceFunctionDTO(DistanceMatrixSourceFunction distanceMatrixSourceFunction) {
        this.function = distanceMatrixSourceFunction.getFunction();
    }
}
