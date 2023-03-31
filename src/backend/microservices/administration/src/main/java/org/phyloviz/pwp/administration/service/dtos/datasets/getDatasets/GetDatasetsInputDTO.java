package org.phyloviz.pwp.administration.service.dtos.datasets.getDatasets;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class GetDatasetsInputDTO {
    private final String projectId;
    private final UserDTO user;
}
