package org.phyloviz.pwp.uploader.http.controllers.models.createProject;

import lombok.Data;
import org.phyloviz.pwp.uploader.service.dtos.createProject.CreateProjectInputDTO;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;

/**
 * Model for the creation of a new project.
 */
@Data
public class CreateProjectInputModel {
    private String name;
    private String description;

    public CreateProjectInputDTO toDTO(BearerTokenAuthentication auth) {
        return new CreateProjectInputDTO(name, description, auth.getName()); // TODO: 3/15/2023 OWNER, not AUTH
    }
}
