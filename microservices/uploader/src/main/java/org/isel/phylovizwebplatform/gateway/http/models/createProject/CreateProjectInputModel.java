package org.isel.phylovizwebplatform.gateway.http.models.createProject;

import org.isel.phylovizwebplatform.gateway.service.dtos.CreateProjectInputDTO;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;

/**
 * Model for the creation of a new project.
 */
public class CreateProjectInputModel {
    private final String name;
    private final String description;

    public CreateProjectInputModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CreateProjectInputDTO toDTO(BearerTokenAuthentication auth) {
        return new CreateProjectInputDTO(name, description, auth.getName()); // TODO: 3/15/2023 OWNER, not AUTH
    }
}
