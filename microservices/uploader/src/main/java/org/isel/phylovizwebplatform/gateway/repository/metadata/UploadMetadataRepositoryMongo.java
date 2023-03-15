package org.isel.phylovizwebplatform.gateway.repository.metadata;

import org.isel.phylovizwebplatform.gateway.repository.metadata.mongo.MetadataMongoRepository;
import org.isel.phylovizwebplatform.gateway.repository.metadata.mongo.ProfileMetadataMongoRepository;
import org.isel.phylovizwebplatform.gateway.repository.metadata.mongo.ProjectMongoRepository;
import org.isel.phylovizwebplatform.gateway.repository.metadata.objects.ProfileMetadata;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.isel.phylovizwebplatform.gateway.repository.project.Project;

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
