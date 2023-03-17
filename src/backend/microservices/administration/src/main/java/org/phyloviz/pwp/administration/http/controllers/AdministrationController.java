package org.phyloviz.pwp.administration.http.controllers;

import java.util.List;
import lombok.AllArgsConstructor;
import org.phyloviz.pwp.administration.http.controllers.models.createProject.CreateProjectInputModel;
import org.phyloviz.pwp.administration.http.controllers.models.createProject.CreateProjectOutputModel;
import org.phyloviz.pwp.administration.http.controllers.models.getProject.GetProjectOutputModel;
import org.phyloviz.pwp.administration.http.controllers.models.getProjects.GetProjectsOutputModel;
import org.phyloviz.pwp.administration.service.AdministrationService;
import org.phyloviz.pwp.administration.service.dtos.ProjectDTO;
import org.phyloviz.pwp.administration.service.dtos.createProject.CreateProjectOutputDTO;
import org.phyloviz.pwp.shared.domain.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the Administration Microservice.
 */
@RestController
@AllArgsConstructor
public class AdministrationController {

    private final AdministrationService administrationService;

    /**
     * Creates a project.
     *
     * @param createProjectInputModel the project to be created following the CreateProjectModel format
     * @return a message indicating that the project was successfully created
     */
    @PostMapping("/projects")
    public CreateProjectOutputModel createProject(
            @RequestBody CreateProjectInputModel createProjectInputModel,
            User user
    ) {
        CreateProjectOutputDTO createProjectOutputDTO = administrationService.createProject(
                createProjectInputModel.toDTO(user)
        );

        return new CreateProjectOutputModel(createProjectOutputDTO);
    }

    /**
     * Deletes a project.
     *
     * @param projectId the id of the project to be deleted
     * @param user      the user that is deleting the project
     */
    @DeleteMapping("/projects/{projectId}")
    public void deleteProject(
            @PathVariable String projectId,
            User user
    ) {
        administrationService.deleteProject(projectId, user.toDTO());
    }

    /**
     * Gets a project.
     *
     * @param projectId the id of the project to be retrieved
     * @param user      the user that is retrieving the project
     * @return the project
     */
    @GetMapping("/projects/{projectId}")
    public GetProjectOutputModel getProject(
            @PathVariable String projectId,
            User user
    ) {
        ProjectDTO projectDTO = administrationService.getProject(projectId, user.toDTO());

        return new GetProjectOutputModel(projectDTO);
    }


    /**
     * Gets all projects belonging to a certain user.
     *
     * @param user the user that is retrieving the projects
     * @return the projects
     */
    @GetMapping("/projects")
    public GetProjectsOutputModel getProjects(
            User user
    ) {
        List<ProjectDTO> projectDTOS = administrationService.getProjects(user.toDTO());

        return new GetProjectsOutputModel(projectDTOS);
    }
}
