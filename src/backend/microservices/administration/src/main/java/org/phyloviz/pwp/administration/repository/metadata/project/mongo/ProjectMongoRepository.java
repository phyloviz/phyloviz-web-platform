package org.phyloviz.pwp.administration.repository.metadata.project.mongo;


import org.phyloviz.pwp.administration.repository.metadata.project.documents.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMongoRepository extends MongoRepository<Project, String> {

    /**
     * Find all projects from a given owner.
     *
     * @param ownerId the id of the owner of the projects
     * @return a list of projects
     */
    List<Project> findAllByOwnerId(String ownerId);
}
