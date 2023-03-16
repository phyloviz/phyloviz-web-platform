package org.phyloviz.pwp.administration.repository.metadata;


import org.phyloviz.pwp.administration.repository.project.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectMongoRepository extends MongoRepository<Project, String> {
}
