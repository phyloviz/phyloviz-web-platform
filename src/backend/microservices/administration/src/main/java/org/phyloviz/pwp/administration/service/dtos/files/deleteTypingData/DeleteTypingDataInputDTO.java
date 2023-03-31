package org.phyloviz.pwp.administration.service.dtos.files.deleteTypingData;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class DeleteTypingDataInputDTO {
    private final String projectId;
    private final String typingDataId;
    private final UserDTO user;
}
