package org.phyloviz.pwp.shared.service.project.file.isolate_data;

import org.phyloviz.pwp.shared.adapters.isolate_data.IsolateDataAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;

import java.util.List;

public interface IsolateDataMetadataService {

    IsolateDataMetadata getIsolateDataMetadata(String projectId, String isolateDataId, String userId);

    IsolateDataMetadata getIsolateDataMetadata(String isolateDataId);

    IsolateDataMetadata getIsolateDataMetadataByAdapterIdOrNull(String isolateDataId, IsolateDataAdapterId adapterId);

    IsolateDataMetadata saveIsolateDataMetadata(IsolateDataMetadata isolateData);

    List<IsolateDataMetadata> getAllIsolateDataMetadata(String isolateDataId);

    /**
     * Find all isolate data metadata from a project id. Only one isolate data metadata per isolate data resource.
     *
     * @param projectId the id of the project
     * @return a list of isolate data metadata
     */
    List<IsolateDataMetadata> getAllIsolateDataMetadataByProjectId(String projectId);

    void deleteIsolateData(IsolateDataMetadata isolateDataMetadata);

    void assertExists(String projectId, String isolateDataId, String userId);
}
