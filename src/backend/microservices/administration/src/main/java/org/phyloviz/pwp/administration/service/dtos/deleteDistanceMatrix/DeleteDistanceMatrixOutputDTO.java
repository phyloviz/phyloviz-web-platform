package org.phyloviz.pwp.administration.service.dtos.deleteDistanceMatrix;

import lombok.Data;

@Data
public class DeleteDistanceMatrixOutputDTO {
    private final String projectId;
    private final String distanceMatrixId;
}
