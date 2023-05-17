package org.phyloviz.pwp.shared.repository.metadata.isolate_data.mongo;

import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IsolateDataMetadataMongoRepository extends MongoRepository<IsolateDataMetadata, String> {

    /**
     * Find the first isolate data metadata with the given id. Analogous to findAny().
     *
     * @param projectId     the id of the project
     * @param isolateDataId the id of the isolate data resource
     * @return a isolate data metadata
     */
    Optional<IsolateDataMetadata> findByProjectIdAndIsolateDataId(String projectId, String isolateDataId);

    /**
     * Find all isolate data metadata from a project id.
     *
     * @param projectId the id of the project
     * @return a list of isolate data metadata
     */
    List<IsolateDataMetadata> findAllByProjectId(String projectId);

    /**
     * Checks if a isolate data metadata exists from a project id and isolate data id.
     *
     * @param projectId     the id of the project
     * @param isolateDataId the id of the isolate data resource
     * @return true if the isolate data metadata exists, false otherwise
     */
    boolean existsByProjectIdAndIsolateDataId(String projectId, String isolateDataId);
}
