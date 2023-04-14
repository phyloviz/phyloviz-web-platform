package org.phyloviz.pwp.administration.service.dtos.distanceMatrices;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.source.DistanceMatrixSourceFunction;

@Data
public class DistanceMatrixDTO {
    private final String distanceMatrixId;
    private final String name;
    private final String sourceType;
    private final DistanceMatrixSourceDTO source;

    public DistanceMatrixDTO(DistanceMatrixMetadata distanceMatrixMetadata) {
        this.distanceMatrixId = distanceMatrixMetadata.getId();
        this.name = distanceMatrixMetadata.getName();
        this.sourceType = distanceMatrixMetadata.getSourceType();
        this.source = switch (distanceMatrixMetadata.getSourceType()) {
            case "function" -> new DistanceMatrixSourceFunctionDTO(
                    (DistanceMatrixSourceFunction) distanceMatrixMetadata.getSource()
            );
            default -> null;
        };
    }
}
