package org.phyloviz.pwp.shared.repository.metadata.isolate_data;

import org.phyloviz.pwp.shared.repository.data.isolate_data.IsolateDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;

import java.util.List;
import java.util.Optional;

public interface IsolateDataMetadataRepository {

    /**
     * Saves an isolate data metadata.
     *
     * @param isolateDataMetadata the isolate data metadata to be stored
     * @return the stored isolate data metadata
     */
    IsolateDataMetadata save(IsolateDataMetadata isolateDataMetadata);

    /**
     * Find any isolate data metadata from a project id and isolate data id.
     *
     * @param projectId     the id of the project
     * @param isolateDataId the id of the isolate data resource
     * @return a distance matrix metadata
     */
    Optional<IsolateDataMetadata> findAnyByProjectIdAndIsolateDataId(String projectId, String isolateDataId);

    /**
     * Find all isolate data metadata from a project id and isolate data id.
     *
     * @param projectId     the id of the project
     * @param isolateDataId the id of the isolate data resource
     * @return a list of distance matrix metadata
     */
    List<IsolateDataMetadata> findAllByProjectIdAndIsolateDataId(String projectId, String isolateDataId);

    /**
     * Find all metadata representations of an isolate data resource.
     *
     * @param isolateDataId the id of the isolate data resource
     * @return a list of isolate data metadata
     */
    List<IsolateDataMetadata> findAllByIsolateDataId(String isolateDataId);

    /**
     * Find an isolate data metadata from its id and repository id.
     *
     * @param isolateDataId the id of the isolate data resource
     * @param repositoryId  the id of the repository
     * @return an isolate data metadata
     */
    Optional<IsolateDataMetadata> findByIsolateDataIdAndRepositoryId(String isolateDataId, IsolateDataDataRepositoryId repositoryId);

    /**
     * Find all isolate data metadata from a project id. Only one isolate data metadata per isolate data resource.
     *
     * @param projectId the id of the project
     * @return a list of isolate data metadata
     */
    List<IsolateDataMetadata> findAllByProjectId(String projectId);

    /**
     * Deletes an isolate data metadata.
     *
     * @param isolateDataMetadata the isolate data metadata to delete
     */
    void delete(IsolateDataMetadata isolateDataMetadata);

    boolean existsByProjectIdAndIsolateDataId(String projectId, String isolateDataId);
}
