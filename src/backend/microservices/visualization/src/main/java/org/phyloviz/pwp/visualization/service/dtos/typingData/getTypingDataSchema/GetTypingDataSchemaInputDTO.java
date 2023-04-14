package org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataSchema;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class GetTypingDataSchemaInputDTO {
    private final String projectId;
    private final String typingDataId;
    private final UserDTO user;
}
