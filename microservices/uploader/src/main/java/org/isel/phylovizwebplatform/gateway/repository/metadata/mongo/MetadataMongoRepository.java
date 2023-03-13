package org.isel.phylovizwebplatform.gateway.repository.metadata.mongo;

import org.isel.phylovizwebplatform.gateway.repository.metadata.objects.Metadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MetadataMongoRepository extends MongoRepository<Metadata, String> {
}
