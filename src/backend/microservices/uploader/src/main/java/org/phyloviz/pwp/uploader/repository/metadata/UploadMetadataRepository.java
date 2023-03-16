package org.phyloviz.pwp.uploader.repository.metadata;

import org.phyloviz.pwp.uploader.repository.metadata.objects.ProfileMetadata;
import org.springframework.stereotype.Repository;
import org.phyloviz.pwp.uploader.repository.project.Project;

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
