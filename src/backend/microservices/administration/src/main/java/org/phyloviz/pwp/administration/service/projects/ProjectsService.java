package org.phyloviz.pwp.administration.service.projects;

import org.phyloviz.pwp.administration.service.dtos.projects.ProjectDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.createProject.CreateProjectInputDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.createProject.CreateProjectOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.deleteProject.DeleteProjectInputDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.deleteProject.DeleteProjectOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.getProject.GetProjectInputDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.getProjects.GetProjectsInputDTO;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;

import java.util.List;

public interface ProjectsService {

    /**
     * Creates a project.
     *
     * @param createProjectInputDTO the input data for the project creation
     * @return the output data from the project creation
     */
    CreateProjectOutputDTO createProject(CreateProjectInputDTO createProjectInputDTO);

    /**
     * Gets a project.
     *
     * @param getProjectInputDTO the input data for the project retrieval
     * @return the project
     */
    ProjectDTO getProject(GetProjectInputDTO getProjectInputDTO);

    /**
     * Deletes a project.
     *
     * @param deleteProjectInputDTO the input data for the project deletion
     * @return the output data for the project deletion
     */
    DeleteProjectOutputDTO deleteProject(DeleteProjectInputDTO deleteProjectInputDTO) throws ProjectNotFoundException;

    /**
     * Gets all projects of a user.
     *
     * @param getProjectsInputDTO the input data for the projects retrieval
     * @return the projects
     */
    List<ProjectDTO> getProjects(GetProjectsInputDTO getProjectsInputDTO);
}
