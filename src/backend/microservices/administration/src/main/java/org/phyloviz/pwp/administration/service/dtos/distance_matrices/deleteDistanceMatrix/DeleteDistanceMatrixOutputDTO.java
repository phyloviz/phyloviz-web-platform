package org.phyloviz.pwp.administration.service.dtos.distance_matrices.deleteDistanceMatrix;

import lombok.Data;

@Data
public class DeleteDistanceMatrixOutputDTO {
    private final String projectId;
    private final String datasetId;
    private final String distanceMatrixId;
}
