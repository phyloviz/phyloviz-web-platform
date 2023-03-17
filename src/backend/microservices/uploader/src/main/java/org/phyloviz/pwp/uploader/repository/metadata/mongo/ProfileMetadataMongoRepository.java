package org.phyloviz.pwp.uploader.repository.metadata.mongo;

import org.phyloviz.pwp.uploader.repository.metadata.documents.ProfileMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileMetadataMongoRepository extends MongoRepository<ProfileMetadata, String> {
}
