package org.phyloviz.pwp.shared.repository.metadata.inference_tree.mongo;

import org.phyloviz.pwp.shared.repository.metadata.inference_tree.documents.InferenceTreeMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InferenceTreeMetadataMongoRepository extends MongoRepository<InferenceTreeMetadata, String> {

    /**
     * Find an inference tree metadata from its resource id.
     *
     * @param resourceId the resource id of the inference tree
     * @return an inference tree metadata
     */
    InferenceTreeMetadata findByResourceId(String resourceId);
}
