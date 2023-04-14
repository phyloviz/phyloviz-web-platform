package org.phyloviz.pwp.visualization.service.dtos.getDistanceMatrix;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;


@Data
public class GetDistanceMatrixInputDTO {
    private final String projectId;
    private final String datasetId;
    private final String distanceMatrixId;
    private final UserDTO user;
}
