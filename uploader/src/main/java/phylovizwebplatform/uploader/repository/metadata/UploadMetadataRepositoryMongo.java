package phylovizwebplatform.uploader.repository.metadata;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import phylovizwebplatform.uploader.repository.metadata.objects.Metadata;

@Repository
@Primary
public class UploadMetadataRepositoryMongo implements UploadMetadataRepository {

    private final IUploadMetadataRepositoryMongo repository;

    public UploadMetadataRepositoryMongo(IUploadMetadataRepositoryMongo repository) {
        this.repository = repository;
    }

    @Override
    public Metadata store(Metadata metadata) {
        return repository.save(metadata);
    }
}
