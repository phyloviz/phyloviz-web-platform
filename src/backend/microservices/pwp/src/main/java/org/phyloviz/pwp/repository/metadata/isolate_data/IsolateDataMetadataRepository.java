package org.phyloviz.pwp.repository.metadata.isolate_data;

import org.phyloviz.pwp.repository.metadata.isolate_data.documents.IsolateDataMetadata;

import java.util.List;
import java.util.Optional;

public interface IsolateDataMetadataRepository {

    /**
     * Saves a isolate data metadata.
     *
     * @param isolateDataMetadata the isolate data metadata to be stored
     * @return the stored isolate data metadata
     */
    IsolateDataMetadata save(IsolateDataMetadata isolateDataMetadata);

    /**
     * Find a isolate data metadata from a project id and isolate data id.
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

    /**
     * Deletes a isolate data metadata.
     *
     * @param isolateDataMetadata the isolate data metadata to delete
     */
    void delete(IsolateDataMetadata isolateDataMetadata);
}
