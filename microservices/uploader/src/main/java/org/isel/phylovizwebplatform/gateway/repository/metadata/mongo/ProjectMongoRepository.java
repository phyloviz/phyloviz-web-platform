package org.isel.phylovizwebplatform.gateway.repository.metadata.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.isel.phylovizwebplatform.gateway.repository.project.Project;

public interface ProjectMongoRepository extends MongoRepository<Project, String> {
}
