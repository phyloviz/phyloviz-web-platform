package org.phyloviz.pwp.shared.repository.metadata.project.mongo;


import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMongoRepository extends MongoRepository<Project, String> {

    /**
     * Find all projects from a given owner.
     *
     * @param ownerId the id of the owner of the projects
     * @return a list of projects
     */
    List<Project> findAllByOwnerId(String ownerId);

    /**
     * Find a project given its id and the id of its owner.
     *
     * @param id      the id of the project
     * @param ownerId the id of the owner of the project
     * @return the project
     */
    Optional<Project> findByIdAndOwnerId(String id, String ownerId);

    /**
     * Checks if a project exists given its id and the id of its owner.
     * @param id the id of the project
     * @param ownerId the id of the owner of the project
     * @return true if the project exists, false otherwise
     */
    Boolean existsByIdAndOwnerId(String id, String ownerId);
}
