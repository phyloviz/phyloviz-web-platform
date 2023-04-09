package org.phyloviz.pwp.shared.repository.metadata.isolateData;

import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.IsolateDataMetadata;

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
     * Find one metadata representation of an isolate data resource.
     *
     * @param isolateDataId the id of the isolate data resource
     * @return an isolate data metadata
     */
    Optional<IsolateDataMetadata> findByIsolateDataId(String isolateDataId);

    /**
     * Deletes an isolate data metadata.
     *
     * @param isolateDataMetadata the isolate data metadata to delete
     */
    void delete(IsolateDataMetadata isolateDataMetadata);

    /**
     * Find all metadata representations of an isolate data resource.
     *
     * @param isolateDataId the id of the isolate data resource
     * @return a list of isolate data metadata
     */
    List<IsolateDataMetadata> findAllByIsolateDataId(String isolateDataId);
}
