package org.phyloviz.pwp.administration.http.models.distanceMatrices;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.distance_matrices.DistanceMatrixDTO;
import org.phyloviz.pwp.administration.service.dtos.distance_matrices.DistanceMatrixSourceFunctionDTO;

@Data
public class DistanceMatrixOutputModel {
    private String distanceMatrixId;
    private String name;
    private String sourceType;
    private DistanceMatrixSourceOutputModel source;

    public DistanceMatrixOutputModel(DistanceMatrixDTO distanceMatrixDTO) {
        this.distanceMatrixId = distanceMatrixDTO.getDistanceMatrixId();
        this.name = distanceMatrixDTO.getName();
        this.sourceType = distanceMatrixDTO.getSourceType();
        this.source = switch (distanceMatrixDTO.getSourceType()) {
            case "function" -> new DistanceMatrixSourceFunctionOutputModel(
                    (DistanceMatrixSourceFunctionDTO) distanceMatrixDTO.getSource()
            );
            default -> throw new IllegalArgumentException("Unknown distance matrix source type: " + distanceMatrixDTO.getSourceType());
        };
    }
}
