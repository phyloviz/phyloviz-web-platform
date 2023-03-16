package org.phyloviz.pwp.administration.service;

import org.phyloviz.pwp.administration.service.dtos.createProject.CreateProjectInputDTO;
import org.phyloviz.pwp.administration.service.dtos.createProject.CreateProjectOutputDTO;
import org.phyloviz.pwp.administration.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.auth.User;
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
     * @param user      user that wants to delete the project
     */
    void deleteProject(String projectId, User user) throws ProjectNotFoundException;
}
