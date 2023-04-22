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

    /**
     * Find a typing data metadata from its id and adapter id.
     * The adapterId is stored as string in the document, so a custom @Query is needed.
     *
     * @param typingDataId the id of the typing data resource
     * @param adapterId    the id of the adapter, as string, like it's stored in the document
     * @return a typing data metadata
     */
    @Query("{ 'typingDataId' : ?0, 'adapterId' : ?1 }")
    Optional<TypingDataMetadata> findByTypingDataIdAndAdapterId(String typingDataId, String adapterId);

    /**
     * Find all typing data metadata from a project id.
     *
     * @param projectId the id of the project
     * @return a list of typing data metadata
     */
    List<TypingDataMetadata> findAllByProjectId(String projectId);

    boolean existsByProjectIdAndTypingDataId(String projectId, String typingDataId);
}
