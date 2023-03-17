package org.phyloviz.pwp.administration.service;

import java.util.List;
import org.phyloviz.pwp.administration.service.dtos.ProjectDTO;
import org.phyloviz.pwp.administration.service.dtos.createProject.CreateProjectInputDTO;
import org.phyloviz.pwp.administration.service.dtos.createProject.CreateProjectOutputDTO;
import org.phyloviz.pwp.administration.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.auth.user.UserDTO;
import org.springframework.stereotype.Service;

/**
 * Service for the Uploader Microservice.
 */
@Service
public interface AdministrationService {

    /**
     * Creates a project.
     *
     * @param createProjectInputDTO the input data for the project creation
     * @return the output data for the project creation
     */
    CreateProjectOutputDTO createProject(CreateProjectInputDTO createProjectInputDTO);

    /**
     * Deletes a project.
     *
     * @param projectId id of the project
     * @param userDTO   user that wants to delete the project
     */
    void deleteProject(String projectId, UserDTO userDTO) throws ProjectNotFoundException;

    /**
     * Gets a project.
     *
     * @param projectId id of the project
     * @param toDTO    user that wants to get the project
     * @return the project
     */
    ProjectDTO getProject(String projectId, UserDTO toDTO);


    /**
     * Gets all projects of a user.
     * @param userDTO user that wants to get the projects
     * @return the projects
     */
    List<ProjectDTO> getProjects(UserDTO userDTO);
}
