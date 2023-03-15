package org.isel.phylovizwebplatform.gateway.repository.metadata.mongo;

import org.isel.phylovizwebplatform.gateway.repository.metadata.objects.ProfileMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileMetadataMongoRepository extends MongoRepository<ProfileMetadata, String> {
}
