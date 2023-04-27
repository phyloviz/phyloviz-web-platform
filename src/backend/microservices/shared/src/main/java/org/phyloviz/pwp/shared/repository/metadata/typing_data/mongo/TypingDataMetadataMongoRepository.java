package org.phyloviz.pwp.shared.repository.metadata.typing_data.mongo;

import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.TypingDataMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypingDataMetadataMongoRepository extends MongoRepository<TypingDataMetadata, String> {

    /**
     * Find the first typing data metadata with the given id. Analogous to findAny().
     *
     * @param projectId    the id of the project
     * @param typingDataId the id of the typing data resource
     * @return a typing data metadata
     */
    Optional<TypingDataMetadata> findFirstByProjectIdAndTypingDataId(String projectId, String typingDataId);

    /**
     * Find all typing data metadata from a project id and typing data id.
     *
     * @param projectId    the id of the project
     * @param typingDataId the id of the typing data
     * @return a list of typing data metadata
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
     * The repositoryId is stored as string in the document, so a custom @Query is needed.
     *
     * @param typingDataId the id of the typing data resource
     * @param repositoryId the id of the repository, as string, like it's stored in the document
     * @return a typing data metadata
     */
    @Query("{ 'typingDataId' : ?0, 'repositoryId' : ?1 }")
    Optional<TypingDataMetadata> findByTypingDataIdAndRepositoryId(String typingDataId, String repositoryId);

    /**
     * Find all typing data metadata from a project id.
     *
     * @param projectId the id of the project
     * @return a list of typing data metadata
     */
    List<TypingDataMetadata> findAllByProjectId(String projectId);

    boolean existsByProjectIdAndTypingDataId(String projectId, String typingDataId);
}
