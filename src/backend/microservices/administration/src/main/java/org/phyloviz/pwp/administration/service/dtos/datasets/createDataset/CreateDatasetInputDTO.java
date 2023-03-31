package org.phyloviz.pwp.administration.service.dtos.datasets.createDataset;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class CreateDatasetInputDTO {
    private final String name;
    private final String description;
    private final String typingDataId;
    private final String isolateDataId;
    private final String projectId;
    private final UserDTO user;
}
