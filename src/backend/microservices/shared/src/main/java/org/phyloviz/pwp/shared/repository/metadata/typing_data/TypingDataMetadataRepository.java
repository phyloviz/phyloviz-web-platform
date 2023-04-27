package org.phyloviz.pwp.shared.repository.metadata.typing_data;

import org.phyloviz.pwp.shared.repository.data.typing_data.TypingDataDataRepositoryId;
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
     * Find any typing data metadata from a project id and typing data id.
     *
     * @param projectId    the id of the project
     * @param typingDataId the id of the typing data resource
     * @return a distance matrix metadata
     */
    Optional<TypingDataMetadata> findAnyByProjectIdAndTypingDataId(String projectId, String typingDataId);

    /**
     * Find all typing data metadata from a project id and typing data id.
     *
     * @param projectId    the id of the project
     * @param typingDataId the id of the typing data resource
     * @return a list of distance matrix metadata
     */
    List<TypingDataMetadata> findAllByProjectIdAndTypingDataId(String projectId, String typingDataId);

    /**
     * Find all metadata representations of a typing data resource.
     *
     * @param typingDataId the id of the typing data resource
     * @return a list of typing data metadata
     */
    List<TypingDataMetadata> findAllByTypingDataId(String typingDataId);

    /**
     * Find a typing data metadata from its id and repository id.
     *
     * @param typingDataId the id of the typing data resource
     * @param repositoryId the id of the repository
     * @return a typing data metadata
     */
    Optional<TypingDataMetadata> findByTypingDataIdAndRepositoryId(String typingDataId, TypingDataDataRepositoryId repositoryId);

    /**
     * Find all typing data metadata from a project id. Only one typing data metadata per typing data resource.
     *
     * @param projectId the id of the project
     * @return a list of typing data metadata
     */
    List<TypingDataMetadata> findAllByProjectId(String projectId);

    boolean existsByProjectIdAndTypingDataId(String projectId, String typingDataId);

    /**
     * Deletes a typing data metadata.
     *
     * @param typingDataMetadata the typing data metadata to delete
     */
    void delete(TypingDataMetadata typingDataMetadata);
}
