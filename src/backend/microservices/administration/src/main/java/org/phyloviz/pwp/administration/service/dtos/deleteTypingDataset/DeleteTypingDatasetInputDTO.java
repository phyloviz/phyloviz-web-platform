package org.phyloviz.pwp.administration.service.dtos.deleteTypingDataset;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class DeleteTypingDatasetInputDTO {
    private final String typingDatasetId;
    private final UserDTO user;
}
