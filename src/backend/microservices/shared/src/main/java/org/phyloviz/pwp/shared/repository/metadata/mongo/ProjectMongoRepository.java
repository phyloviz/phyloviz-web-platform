package org.phyloviz.pwp.shared.repository.metadata.mongo;


import java.util.List;
import org.phyloviz.pwp.shared.repository.metadata.documents.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMongoRepository extends MongoRepository<Project, String> {
    List<Project> findAllByOwner(String owner);
}
