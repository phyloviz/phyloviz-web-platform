package phylovizwebplatform.uploader.repository.metadata;

import org.springframework.data.mongodb.repository.MongoRepository;
import phylovizwebplatform.uploader.repository.metadata.objects.Metadata;

public interface IUploadMetadataRepositoryMongo extends MongoRepository<Metadata, String> {
}

