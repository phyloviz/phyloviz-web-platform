package org.phyloviz.pwp.shared.service.dtos.distance_matrix;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.source.DistanceMatrixSourceFunction;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.source.DistanceMatrixSourceType;

@Data
public class DistanceMatrixInfo {
    private final String distanceMatrixId;
    private final String name;
    private final DistanceMatrixSourceType sourceType;
    private final DistanceMatrixSourceInfo source;

    public DistanceMatrixInfo(DistanceMatrixMetadata distanceMatrixMetadata) {
        this.distanceMatrixId = distanceMatrixMetadata.getId();
        this.name = distanceMatrixMetadata.getName();
        this.sourceType = distanceMatrixMetadata.getSourceType();
        this.source = switch (distanceMatrixMetadata.getSourceType()) {
            case FUNCTION -> new DistanceMatrixSourceFunctionInfo(
                    (DistanceMatrixSourceFunction) distanceMatrixMetadata.getSource()
            );
        };
    }
}
