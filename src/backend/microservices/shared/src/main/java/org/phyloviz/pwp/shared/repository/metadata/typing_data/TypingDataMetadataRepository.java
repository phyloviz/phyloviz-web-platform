package org.phyloviz.pwp.shared.repository.metadata.typing_data;

import org.phyloviz.pwp.shared.adapters.typing_data.TypingDataAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.TypingDataMetadata;

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
     * Find any typing data metadata from its id.
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

    /**
     * Find a typing data metadata from its id and adapter id.
     *
     * @param typingDataId the id of the typing data resource
     * @param adapterId    the id of the adapter
     * @return a typing data metadata
     */
    Optional<TypingDataMetadata> findByTypingDataIdAndAdapterId(String typingDataId, TypingDataAdapterId adapterId);

    /**
     * Find all typing data metadata from a project id. Only one typing data metadata per typing data resource.
     *
     * @param projectId the id of the project
     * @return a list of typing data metadata
     */
    List<TypingDataMetadata> findAllByProjectId(String projectId);

    Boolean existsByProjectIdAndTypingDataId(String projectId, String typingDataId);

    /**
     * Deletes a typing data metadata.
     *
     * @param typingDataMetadata the typing data metadata to delete
     */
    void delete(TypingDataMetadata typingDataMetadata);
}
