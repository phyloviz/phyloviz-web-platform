package org.phyloviz.pwp.uploader.repository.metadata.mongo;

import org.phyloviz.pwp.uploader.repository.project.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectMongoRepository extends MongoRepository<Project, String> {
}
