package org.phyloviz.pwp.shared.repository.metadata.typing_dataset.mongo;

import org.phyloviz.pwp.shared.repository.metadata.typing_dataset.documents.TypingDatasetMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TypingDatasetMetadataMongoRepository extends MongoRepository<TypingDatasetMetadata, String> {

    /**
     * Find a typing dataset metadata from its resource id.
     *
     * @param resourceId the resource id of the typing dataset
     * @return a typing dataset metadata
     */
    TypingDatasetMetadata findByResourceId(String resourceId);
}
