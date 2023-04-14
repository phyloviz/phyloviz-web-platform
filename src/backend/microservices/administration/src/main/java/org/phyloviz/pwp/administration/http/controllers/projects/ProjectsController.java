package org.phyloviz.pwp.administration.http.controllers.projects;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.http.models.projects.createProject.CreateProjectInputModel;
import org.phyloviz.pwp.administration.http.models.projects.createProject.CreateProjectOutputModel;
import org.phyloviz.pwp.administration.http.models.projects.deleteProject.DeleteProjectOutputModel;
import org.phyloviz.pwp.administration.http.models.projects.getProject.GetProjectOutputModel;
import org.phyloviz.pwp.administration.http.models.projects.getProjects.GetProjectsOutputModel;
import org.phyloviz.pwp.administration.service.dtos.projects.ProjectDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.createProject.CreateProjectOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.deleteProject.DeleteProjectInputDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.deleteProject.DeleteProjectOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.getProject.GetProjectInputDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.getProjects.GetProjectsInputDTO;
import org.phyloviz.pwp.administration.service.projects.ProjectsService;
import org.phyloviz.pwp.shared.domain.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that handles requests related to projects.
 */
@RestController
@RequiredArgsConstructor
public class ProjectsController {

    private final ProjectsService projectsService;

    /**
     * Creates a project.
     *
     * @param createProjectInputModel the input model containing the data of the project to be created
     * @param user                    the user that is creating the project
     * @return the output model containing the data of the created project
     */
    @PostMapping("/projects")
    public CreateProjectOutputModel createProject(
            @RequestBody CreateProjectInputModel createProjectInputModel,
            User user
    ) {
        CreateProjectOutputDTO createProjectOutputDTO = projectsService.createProject(
                createProjectInputModel.toDTO(user)
        );

        return new CreateProjectOutputModel(createProjectOutputDTO);
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
        ProjectDTO projectDTO = projectsService.getProject(
                new GetProjectInputDTO(projectId, user.toDTO())
        );

        return new GetProjectOutputModel(projectDTO);
    }

    /**
     * Deletes a project.
     *
     * @param projectId the id of the project to be deleted
     * @param user      the user that is deleting the project
     * @return the output model containing the data of the deleted project
     */
    @DeleteMapping("/projects/{projectId}")
    public DeleteProjectOutputModel deleteProject(
            @PathVariable String projectId,
            User user
    ) {
        DeleteProjectOutputDTO deleteProjectOutputDTO = projectsService.deleteProject(
                new DeleteProjectInputDTO(projectId, user.toDTO())
        );

        return new DeleteProjectOutputModel(deleteProjectOutputDTO);
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
        List<ProjectDTO> projectDTOS = projectsService.getProjects(
                new GetProjectsInputDTO(user.toDTO())
        );

        return new GetProjectsOutputModel(projectDTOS);
    }
}