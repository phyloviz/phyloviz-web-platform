package org.phyloviz.pwp.administration.http.controllers;

import org.phyloviz.pwp.administration.http.controllers.models.createProject.CreateProjectInputModel;
import org.phyloviz.pwp.administration.http.controllers.models.createProject.CreateProjectOutputModel;
import org.phyloviz.pwp.administration.service.AdministrationService;
import org.phyloviz.pwp.administration.service.dtos.createProject.CreateProjectOutputDTO;
import org.phyloviz.pwp.shared.auth.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the Administration Microservice.
 */
@RestController
public class AdministrationController {

    private final AdministrationService administrationService;

    public AdministrationController(AdministrationService administrationService) {
        this.administrationService = administrationService;
    }

    /**
     * Creates a project.
     *
     * @param createProjectInputModel the project to be created following the CreateProjectModel format
     * @return a message indicating that the project was successfully created
     */
    @PostMapping("/project")
    public CreateProjectOutputModel createProject(
            @RequestBody CreateProjectInputModel createProjectInputModel,
            User user
    ) {
        CreateProjectOutputDTO createProjectOutputDTO = administrationService.createProject(
                createProjectInputModel.toDTO(user)
        );

        return new CreateProjectOutputModel(createProjectOutputDTO);
    }

    @DeleteMapping("/project/{projectId}")
    public void deleteProject(
            @PathVariable String projectId,
            User user
    ) {
        administrationService.deleteProject(projectId, user);
    }
}
