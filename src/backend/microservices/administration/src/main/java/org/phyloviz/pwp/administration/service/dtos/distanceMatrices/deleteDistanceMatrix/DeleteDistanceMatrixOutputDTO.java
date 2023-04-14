package org.phyloviz.pwp.administration.service.dtos.distanceMatrices.deleteDistanceMatrix;

import lombok.Data;

@Data
public class DeleteDistanceMatrixOutputDTO {
    private final String projectId;
    private final String datasetId;
    private final String distanceMatrixId;
}
