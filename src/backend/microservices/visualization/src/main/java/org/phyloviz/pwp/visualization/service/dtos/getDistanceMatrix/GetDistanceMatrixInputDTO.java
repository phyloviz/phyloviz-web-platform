package org.phyloviz.pwp.visualization.service.dtos.getDistanceMatrix;

import lombok.Data;


@Data
public class GetDistanceMatrixInputDTO {
    private final String projectId;
    private final String datasetId;
    private final String distanceMatrixId;
}
