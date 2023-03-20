package org.phyloviz.pwp.administration.service.dtos.deleteInferenceTree;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class DeleteInferenceTreeInputDTO {
    private final String inferenceTreeId;
    private final UserDTO user;
}
