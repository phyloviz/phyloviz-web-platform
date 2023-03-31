package org.phyloviz.pwp.administration.service.dtos.datasets.deleteDataset;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class DeleteDatasetInputDTO {
    private final String projectId;
    private final String datasetId;
    private final UserDTO user;
}
