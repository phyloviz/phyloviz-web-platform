package org.phyloviz.pwp.shared.repository.metadata.isolate_data.mongo;

import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IsolateDataMetadataMongoRepository extends MongoRepository<IsolateDataMetadata, String> {

    /**
     * Find one metadata representation of an isolate data resource.
     *
     * @param isolateDataId the id of the isolate data resource
     * @return an isolate data metadata
     */
    Optional<IsolateDataMetadata> findByIsolateDataId(String isolateDataId);

    /**
     * Find all metadata representations of an isolate data resource.
     *
     * @param isolateDataId the id of the isolate data resource
     * @return a list of isolate data metadata
     */
    List<IsolateDataMetadata> findAllByIsolateDataId(String isolateDataId);
}
