package org.phyloviz.pwp.administration.service.dtos.deleteProject;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class DeleteProjectInputDTO {
    private final String projectId;
    private final UserDTO user;
}
