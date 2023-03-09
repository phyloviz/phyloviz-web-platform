package phylovizwebplatform.uploader.repository.metadata;

import org.springframework.stereotype.Repository;
import phylovizwebplatform.uploader.repository.metadata.objects.Metadata;

@Repository
public interface UploadMetadataRepository {

    /**
     * Stores the metadata.
     *
     * @param metadata the metadata to be stored
     */
    Metadata store(Metadata metadata);
}
