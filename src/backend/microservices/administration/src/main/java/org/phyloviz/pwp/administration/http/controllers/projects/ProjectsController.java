package org.phyloviz.pwp.administration.http.controllers.projects;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.http.models.projects.create_project.CreateProjectInputModel;
import org.phyloviz.pwp.administration.http.models.projects.create_project.CreateProjectOutputModel;
import org.phyloviz.pwp.administration.http.models.projects.delete_project.DeleteProjectOutputModel;
import org.phyloviz.pwp.administration.http.models.projects.get_project.GetProjectOutputModel;
import org.phyloviz.pwp.administration.http.models.projects.get_projects.GetProjectsOutputModel;
import org.phyloviz.pwp.administration.http.models.projects.update_project.UpdateProjectInputModel;
import org.phyloviz.pwp.administration.http.models.projects.update_project.UpdateProjectOutputModel;
import org.phyloviz.pwp.administration.service.dtos.project.CreateProjectOutput;
import org.phyloviz.pwp.administration.service.dtos.project.FullProjectInfo;
import org.phyloviz.pwp.administration.service.dtos.project.UpdateProjectOutput;
import org.phyloviz.pwp.administration.service.project.ProjectService;
import org.phyloviz.pwp.shared.domain.User;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    private final ProjectService projectService;

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
        CreateProjectOutput createProjectOutput = projectService.createProject(
                createProjectInputModel.getName(),
                createProjectInputModel.getDescription(),
                user.getId()
        );

        return new CreateProjectOutputModel(createProjectOutput);
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
        FullProjectInfo fullProjectInfo = projectService.getFullProjectInfo(projectId, user.getId());

        return new GetProjectOutputModel(fullProjectInfo);
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
        projectService.deleteProject(projectId, user.getId());

        return new DeleteProjectOutputModel(projectId);
    }

    /**
     * Updates a project.
     *
     * @param projectId the id of the project to be updated
     * @param user      the user that is updating the project
     * @return information about the update
     */
    @PatchMapping("/projects/{projectId}")
    public UpdateProjectOutputModel updateProject(
            @PathVariable String projectId,
            @RequestBody UpdateProjectInputModel updateProjectInputModel,
            User user
    ) {
        UpdateProjectOutput updateProjectOutput = projectService.updateProject(
                updateProjectInputModel.getName(),
                updateProjectInputModel.getDescription(),
                projectId, user.getId()
        );

        return new UpdateProjectOutputModel(updateProjectOutput);
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
        List<Project> projects = projectService.getProjects(user.getId());

        return new GetProjectsOutputModel(projects);
    }
}