package org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataSchema;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class GetIsolateDataSchemaInputDTO {
    private final String projectId;
    private final String isolateDataId;
    private final UserDTO user;
}
