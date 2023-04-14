package org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataProfiles;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class GetTypingDataProfilesInputDTO {
    private final String projectId;
    private final String typingDataId;
    private final int limit;
    private final int offset;
    private final UserDTO user;
}
