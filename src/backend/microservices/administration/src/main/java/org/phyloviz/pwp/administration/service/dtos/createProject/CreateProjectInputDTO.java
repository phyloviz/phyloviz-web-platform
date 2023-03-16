package org.phyloviz.pwp.administration.service.dtos.createProject;

import lombok.Data;
import org.phyloviz.pwp.shared.auth.UserDTO;

@Data
public class CreateProjectInputDTO {
    private final String name;
    private final String description;
    private final UserDTO owner;
}
