package org.phyloviz.pwp.shared.service.dtos.distanceMatrix;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.source.DistanceMatrixSourceFunction;

@Data
public class DistanceMatrixMetadataDTO {
    private final String distanceMatrixId;
    private final String name;
    private final String sourceType;
    private final DistanceMatrixSourceDTO source;

    public DistanceMatrixMetadataDTO(DistanceMatrixMetadata distanceMatrixMetadata) {
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
