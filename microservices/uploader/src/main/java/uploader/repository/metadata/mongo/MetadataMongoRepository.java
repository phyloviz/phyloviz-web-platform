package uploader.repository.metadata.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import uploader.repository.metadata.objects.Metadata;

public interface MetadataMongoRepository extends MongoRepository<Metadata, String> {
}
