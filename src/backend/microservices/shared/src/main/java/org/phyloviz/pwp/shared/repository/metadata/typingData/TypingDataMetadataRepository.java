package org.phyloviz.pwp.shared.repository.metadata.typingData;

import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataMetadata;

import java.util.List;
import java.util.Optional;

public interface TypingDataMetadataRepository {

    /**
     * Saves a typing data metadata.
     *
     * @param typingDataMetadata the typing data metadata to be stored
     * @return the stored typing data metadata
     */
    TypingDataMetadata save(TypingDataMetadata typingDataMetadata);

    /**
     * Deletes a typing data metadata.
     *
     * @param typingDataMetadata the typing data metadata to delete
     */
    void delete(TypingDataMetadata typingDataMetadata);

    /**
     * Find one metadata representation of a typing data resource.
     *
     * @param typingDataId the id of the typing data resource
     * @return a typing data metadata
     */
    Optional<TypingDataMetadata> findByTypingDataId(String typingDataId);

    /**
     * Find all metadata representations of a typing data resource.
     *
     * @param typingDataId the id of the typing data resource
     * @return a list of typing data metadata
     */
    List<TypingDataMetadata> findAllByTypingDataId(String typingDataId);
}
