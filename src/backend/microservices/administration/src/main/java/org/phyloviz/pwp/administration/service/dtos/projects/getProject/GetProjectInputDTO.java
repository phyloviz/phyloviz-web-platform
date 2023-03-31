package org.phyloviz.pwp.administration.service.dtos.projects.getProject;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class GetProjectInputDTO {
    private final String projectId;
    private final UserDTO user;
}
