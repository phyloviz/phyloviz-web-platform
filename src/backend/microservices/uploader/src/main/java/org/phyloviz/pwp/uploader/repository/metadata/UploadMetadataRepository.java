package org.phyloviz.pwp.uploader.repository.metadata;

import org.phyloviz.pwp.uploader.repository.metadata.documents.ProfileMetadata;
import org.springframework.stereotype.Repository;

/**
 * Repository for the metadata of the Uploader Microservice.
 */
@Repository
public interface UploadMetadataRepository {

    /**
     * Stores the given profile metadata.
     *
     * @param profileMetadata the profile metadata to be stored
     * @return the stored profile metadata
     */
    ProfileMetadata store(ProfileMetadata profileMetadata);

}
