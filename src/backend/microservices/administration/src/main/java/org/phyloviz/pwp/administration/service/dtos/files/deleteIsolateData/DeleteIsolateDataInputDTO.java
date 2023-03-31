package org.phyloviz.pwp.administration.service.dtos.files.deleteIsolateData;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class DeleteIsolateDataInputDTO {
    private final String projectId;
    private final String isolateDataId;
    private final UserDTO user;
}
