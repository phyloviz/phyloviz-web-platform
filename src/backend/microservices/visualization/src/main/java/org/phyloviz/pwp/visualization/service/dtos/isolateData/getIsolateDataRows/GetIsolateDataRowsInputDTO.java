package org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataRows;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class GetIsolateDataRowsInputDTO {
    private final String projectId;
    private final String isolateDataId;
    private final int limit;
    private final int offset;
    private final UserDTO user;
}
