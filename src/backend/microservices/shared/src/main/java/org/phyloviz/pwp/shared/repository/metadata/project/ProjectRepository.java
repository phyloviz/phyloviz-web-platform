package org.phyloviz.pwp.shared.repository.metadata.project;

import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    /**
     * Saves a project.
     *
     * @param project the project to save
     * @return the saved project
     */
    Project save(Project project);

    /**
     * Deletes a project.
     *
     * @param project the project to delete
     */
    void delete(Project project);

    /**
     * Finds a project given its id.
     *
     * @param id the id of the project
     * @return the project
     */
    Optional<Project> findById(String id);

    /**
     * Find all projects from a given owner.
     *
     * @param ownerId the id of the owner of the projects
     * @return a list of projects
     */
    List<Project> findAllByOwnerId(String ownerId);
}
