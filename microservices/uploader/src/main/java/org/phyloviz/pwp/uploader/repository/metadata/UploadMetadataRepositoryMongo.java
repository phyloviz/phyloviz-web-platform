package org.phyloviz.pwp.uploader.repository.metadata;

import org.phyloviz.pwp.uploader.repository.metadata.mongo.ProfileMetadataMongoRepository;
import org.phyloviz.pwp.uploader.repository.metadata.mongo.ProjectMongoRepository;
import org.phyloviz.pwp.uploader.repository.metadata.objects.ProfileMetadata;
import org.phyloviz.pwp.uploader.repository.project.Project;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the {@link UploadMetadataRepository} interface that stores the metadata in a MongoDB database.
 */
@Repository
@Primary
public class UploadMetadataRepositoryMongo implements UploadMetadataRepository {

    private final ProfileMetadataMongoRepository profileMetadataMongoRepository;
    private final ProjectMongoRepository projectMongoRepository;

    public UploadMetadataRepositoryMongo(
            ProfileMetadataMongoRepository profileMetadataMongoRepository,
            ProjectMongoRepository projectMongoRepository
    ) {
        this.profileMetadataMongoRepository = profileMetadataMongoRepository;
        this.projectMongoRepository = projectMongoRepository;
    }

    @Override
    public ProfileMetadata store(ProfileMetadata profileMetadata) {
        return profileMetadataMongoRepository.save(profileMetadata);
    }

    @Override
    public Project storeProject(Project project) {
        return projectMongoRepository.save(project);
    }
}
