package org.phyloviz.pwp.uploader.repository.metadata;

import lombok.AllArgsConstructor;
import org.phyloviz.pwp.uploader.repository.metadata.documents.ProfileMetadata;
import org.phyloviz.pwp.uploader.repository.metadata.mongo.ProfileMetadataMongoRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the {@link UploadMetadataRepository} interface that stores the metadata in a MongoDB database.
 */
@Repository
@Primary
@AllArgsConstructor
public class UploadMetadataRepositoryMongo implements UploadMetadataRepository {

    private final ProfileMetadataMongoRepository profileMetadataMongoRepository;

    @Override
    public ProfileMetadata store(ProfileMetadata profileMetadata) {
        return profileMetadataMongoRepository.save(profileMetadata);
    }

}
