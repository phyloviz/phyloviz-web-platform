package org.phyloviz.pwp.administration.service.dtos.deleteDistanceMatrix;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class DeleteDistanceMatrixInputDTO {
    private final String distanceMatrixId;
    private final UserDTO user;
}
