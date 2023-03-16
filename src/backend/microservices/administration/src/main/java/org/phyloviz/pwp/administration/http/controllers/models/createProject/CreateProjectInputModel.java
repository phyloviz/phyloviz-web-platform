package org.phyloviz.pwp.administration.http.controllers.models.createProject;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.createProject.CreateProjectInputDTO;
import org.phyloviz.pwp.shared.auth.User;

/**
 * Model for the creation of a new project.
 */
@Data
public class CreateProjectInputModel {
    private final String name;
    private final String description;

    public CreateProjectInputDTO toDTO(User user) {
        return new CreateProjectInputDTO(name, description, user.toDTO());
    }
}
