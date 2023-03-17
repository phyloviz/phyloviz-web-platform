package org.phyloviz.pwp.uploader.repository.metadata.mongo;

import org.phyloviz.pwp.uploader.repository.metadata.documents.Metadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MetadataMongoRepository extends MongoRepository<Metadata, String> {
}
