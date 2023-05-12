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
     * @param projectId the id of the project
     * @param typingDataId the id of the typing data resource
     * @return true if the typing data metadata exists, false otherwise
     */
    boolean existsByProjectIdAndTypingDataId(String projectId, String typingDataId);
}
