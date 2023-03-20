package org.phyloviz.pwp.shared.repository.metadata.project;

import org.phyloviz.pwp.shared.repository.metadata.Metadata;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;

import java.util.List;

public interface ProjectRepository {
    /**
     * Saves a project.
     *
     * @param project the project to save
     * @return the saved project
     */
    Project save(Project project);

    /**
     * Finds a project given its id.
     *
     * @param id the id of the project
     * @return the project
     */
    Project findById(String id);

    /**
     * Deletes a project.
     *
     * @param project the project to delete
     */
    void delete(Project project);

    /**
     * Find all representations of a resource.
     *
     * @param resourceId the id of the resource to find representations of
     * @param resourceClass the class of the resource to find representations of
     */
    <T extends Metadata> List<T> findAllResourceRepresentations(String resourceId, Class<T> resourceClass);

    /**
     * Find all projects from a given owner.
     *
     * @param ownerId the id of the owner of the projects
     * @return a list of projects
     */
    List<Project> findAllProjectsByOwnerId(String ownerId);
}
