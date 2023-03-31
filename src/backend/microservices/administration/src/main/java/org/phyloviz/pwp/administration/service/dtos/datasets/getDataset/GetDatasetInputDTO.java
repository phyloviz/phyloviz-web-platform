package org.phyloviz.pwp.administration.service.dtos.datasets.getDataset;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class GetDatasetInputDTO {
    private final String projectId;
    private final String datasetId;
    private final UserDTO user;
}
