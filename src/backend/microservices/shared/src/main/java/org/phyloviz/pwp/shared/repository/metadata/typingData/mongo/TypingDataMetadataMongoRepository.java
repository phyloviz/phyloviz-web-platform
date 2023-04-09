package org.phyloviz.pwp.shared.repository.metadata.typingData.mongo;

import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
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
}
