package org.phyloviz.pwp.shared.repository.metadata.mongo;


import org.phyloviz.pwp.shared.repository.metadata.documents.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMongoRepository extends MongoRepository<Project, String> {

    /**
     * Find all projects given an owner.
     *
     * @param owner the owner of the projects
     * @return a list of projects
     */
    List<Project> findAllByOwner(String owner);
}
