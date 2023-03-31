package org.phyloviz.pwp.administration.service.dtos.distance_matrices.deleteDistanceMatrix;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class DeleteDistanceMatrixInputDTO {
    private final String projectId;
    private final String datasetId;
    private final String distanceMatrixId;
    private final UserDTO user;
}
