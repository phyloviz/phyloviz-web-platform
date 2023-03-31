package org.phyloviz.pwp.administration.service.dtos.projects.getProjects;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class GetProjectsInputDTO {
    private final UserDTO user;
}
