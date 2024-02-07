package org.phyloviz.pwp.repository.metadata.typing_data;

import org.phyloviz.pwp.repository.metadata.typing_data.documents.TypingDataMetadata;

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
     * Find a typing data metadata from a project id and typing data id.
     *
     * @param projectId    the id of the project
     * @param typingDataId the id of the typing data resource
     * @return a typing data metadata
     */
    Optional<TypingDataMetadata> findByProjectIdAndTypingDataId(String projectId, String typingDataId);

    /**
     * Find all typing data metadata from a project id.
     *
     * @param projectId the id of the project
     * @return a list of typing data metadata
     */
    List<TypingDataMetadata> findAllByProjectId(String projectId);

    /**
     * Checks if a typing data metadata exists from a project id and typing data id.
     *
     * @param projectId    the id of the project
     * @param typingDataId the id of the typing data resource
     * @return true if the typing data metadata exists, false otherwise
     */
    boolean existsByProjectIdAndTypingDataId(String projectId, String typingDataId);

    /**
     * Deletes a typing data metadata.
     *
     * @param typingDataMetadata the typing data metadata to delete
     */
    void delete(TypingDataMetadata typingDataMetadata);
}
