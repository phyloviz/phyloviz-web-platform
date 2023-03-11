package uploader.repository.metadata;

import org.springframework.stereotype.Repository;
import uploader.repository.metadata.objects.Metadata;
import uploader.repository.project.Project;

/**
 * Repository for the metadata of the Uploader Microservice.
 */
@Repository
public interface UploadMetadataRepository {

    /**
     * Stores the given metadata.
     *
     * @param metadata the metadata to be stored
     */
    Metadata store(Metadata metadata);

    /**
     * Stores a project metadata.
     *
     * @param project the project to be stored
     */
    void storeProject(Project project);
}
