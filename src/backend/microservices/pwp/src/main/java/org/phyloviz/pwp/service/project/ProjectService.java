package org.phyloviz.pwp.service.project;

import org.phyloviz.pwp.service.dtos.project.CreateProjectOutput;
import org.phyloviz.pwp.service.dtos.project.FullProjectInfo;
import org.phyloviz.pwp.service.dtos.project.UpdateProjectOutput;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;

import java.util.List;

/**
 * Service for managing projects.
 */
public interface ProjectService {

    /**
     * Creates a new project.
     *
     * @param name        the name of the project
     * @param description the description of the project
     * @param userId      the id of the user that is creating the project
     * @return the output of the project creation
     */
    CreateProjectOutput createProject(String name, String description, String userId);

    /**
     * Gets the full information of a project.
     *
     * @param projectId the id of the project
     * @param userId    the id of the user that is requesting the project information
     * @return the full information of the project
     */
    FullProjectInfo getFullProjectInfo(String projectId, String userId);

    /**
     * Gets the projects of a user.
     *
     * @param userId the id of the user
     * @return the projects of the user
     */
    List<Project> getProjects(String userId);

    /**
     * Deletes a project.
     *
     * @param projectId the id of the project
     * @param userId    the id of the user that is deleting the project
     */
    void deleteProject(String projectId, String userId);

    /**
     * Updates a project.
     *
     * @param name        the name of the project
     * @param description the description of the project
     * @param projectId   the id of the project
     * @param userId      the id of the user that is updating the project
     * @return the output of the project update
     */
    UpdateProjectOutput updateProject(String name, String description, String projectId, String userId);
}
