package uploader.repository.metadata.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import uploader.repository.project.Project;

public interface ProjectMongoRepository extends MongoRepository<Project, String> {
}
