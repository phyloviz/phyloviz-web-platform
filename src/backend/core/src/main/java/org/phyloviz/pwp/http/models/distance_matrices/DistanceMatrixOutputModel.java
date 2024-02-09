package org.phyloviz.pwp.http.models.distance_matrices;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.distance_matrix.DistanceMatrixInfo;
import org.phyloviz.pwp.service.dtos.distance_matrix.DistanceMatrixSourceFunctionInfo;

@Data
public class DistanceMatrixOutputModel {
    private String distanceMatrixId;
    private String name;
    private String sourceType;
    private DistanceMatrixSourceOutputModel source;

    public DistanceMatrixOutputModel(DistanceMatrixInfo distanceMatrixInfo) {
        this.distanceMatrixId = distanceMatrixInfo.getDistanceMatrixId();
        this.name = distanceMatrixInfo.getName();
        this.sourceType = distanceMatrixInfo.getSourceType().name().toLowerCase();
        this.source = switch (distanceMatrixInfo.getSourceType()) {
            case FUNCTION -> new DistanceMatrixSourceFunctionOutputModel(
                    (DistanceMatrixSourceFunctionInfo) distanceMatrixInfo.getSource()
            );
        };
    }
}
