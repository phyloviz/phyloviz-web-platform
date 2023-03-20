package org.phyloviz.pwp.shared.repository.metadata.inference_tree;

import org.phyloviz.pwp.shared.repository.metadata.inference_tree.documents.InferenceTreeMetadata;

public interface InferenceTreeMetadataRepository {

    /**
     * Deletes an inference tree.
     *
     * @param inferenceTreeMetadata the inference tree to delete
     */
    void deleteInferenceTree(InferenceTreeMetadata inferenceTreeMetadata);

    /**
     * Find an inference tree metadata from its resource id.
     *
     * @param resourceId the resource id of the inference tree
     * @return an inference tree metadata
     */
    InferenceTreeMetadata findByResourceId(String resourceId);
}
