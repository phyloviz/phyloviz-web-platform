package org.phyloviz.pwp.administration.http.models.projects.createProject;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.projects.createProject.CreateProjectInputDTO;
import org.phyloviz.pwp.shared.domain.User;

/**
 * Model for the creation of a new project.
 */
@Data
public class CreateProjectInputModel {
    private String name;
    private String description;

    public CreateProjectInputDTO toDTO(User user) {
        return new CreateProjectInputDTO(name, description, user.toDTO());
    }
}