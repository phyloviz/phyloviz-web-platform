package org.phyloviz.pwp.administration.http.models.distanceMatrices;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.distanceMatrix.DistanceMatrixMetadataDTO;
import org.phyloviz.pwp.shared.service.dtos.distanceMatrix.DistanceMatrixSourceFunctionDTO;

@Data
public class DistanceMatrixOutputModel {
    private String distanceMatrixId;
    private String name;
    private String sourceType;
    private DistanceMatrixSourceOutputModel source;

    public DistanceMatrixOutputModel(DistanceMatrixMetadataDTO distanceMatrixMetadataDTO) {
        this.distanceMatrixId = distanceMatrixMetadataDTO.getDistanceMatrixId();
        this.name = distanceMatrixMetadataDTO.getName();
        this.sourceType = distanceMatrixMetadataDTO.getSourceType();
        this.source = switch (distanceMatrixMetadataDTO.getSourceType()) {
            case "function" -> new DistanceMatrixSourceFunctionOutputModel(
                    (DistanceMatrixSourceFunctionDTO) distanceMatrixMetadataDTO.getSource()
            );
            default -> throw new IllegalArgumentException(
                    "Unknown distance matrix source type: " + distanceMatrixMetadataDTO.getSourceType()
            );
        };
    }
}
