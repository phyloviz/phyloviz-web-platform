package org.isel.phylovizwebplatform.gateway.repository.metadata;

import org.isel.phylovizwebplatform.gateway.repository.metadata.objects.Metadata;
import org.isel.phylovizwebplatform.gateway.repository.metadata.objects.ProfileMetadata;
import org.springframework.stereotype.Repository;
import org.isel.phylovizwebplatform.gateway.repository.project.Project;

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

    /**
     * Stores a project metadata.
     *
     * @param project the project to be stored
     * @return the stored project
     */
    Project storeProject(Project project);
}
