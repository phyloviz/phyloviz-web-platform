package org.phyloviz.pwp.shared.repository.metadata.isolate_data.mongo;

import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
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

    /**
     * Find an isolate data metadata from its id and adapter id.
     * The adapterId is stored as string in the document, so a custom @Query is needed.
     *
     * @param isolateDataId the id of the isolate data resource
     * @param adapterId     the id of the adapter, as string, like it's stored in the document
     * @return an isolate data metadata
     */
    @Query("{ 'isolateDataId' : ?0, 'adapterId' : ?1 }")
    Optional<IsolateDataMetadata> findByIsolateDataIdAndAdapterId(String isolateDataId, String adapterId);

    /**
     * Find all isolate data metadata from a project id.
     *
     * @param projectId the id of the project
     * @return a list of isolate data metadata
     */
    List<IsolateDataMetadata> findAllByProjectId(String projectId);

    Boolean existsByProjectIdAndIsolateDataId(String projectId, String isolateDataId);
}
