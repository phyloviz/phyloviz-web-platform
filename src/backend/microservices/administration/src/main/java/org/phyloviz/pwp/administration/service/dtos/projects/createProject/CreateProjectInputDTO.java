package org.phyloviz.pwp.administration.service.dtos.projects.createProject;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class CreateProjectInputDTO {
    private final String name;
    private final String description;
    private final UserDTO owner;
}
