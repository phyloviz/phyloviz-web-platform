package org.phyloviz.pwp.shared.repository.metadata.typing_dataset;

import org.phyloviz.pwp.shared.repository.metadata.typing_dataset.documents.TypingDatasetMetadata;

public interface TypingDatasetMetadataRepository {

    /**
     * Saves a typing dataset metadata.
     *
     * @param typingDatasetMetadata the typing dataset metadata to be stored
     * @return the stored typing dataset metadata
     */
    TypingDatasetMetadata save(TypingDatasetMetadata typingDatasetMetadata);

    /**
     * Deletes a typing dataset metadata.
     *
     * @param typingDatasetMetadata the typing dataset metadata to delete
     */
    void delete(TypingDatasetMetadata typingDatasetMetadata);

    /**
     * Find a typing dataset metadata from its resource id.
     *
     * @param resourceId the resource id of the typing dataset
     * @return a typing dataset metadata
     */
    TypingDatasetMetadata findByResourceId(String resourceId);
}
