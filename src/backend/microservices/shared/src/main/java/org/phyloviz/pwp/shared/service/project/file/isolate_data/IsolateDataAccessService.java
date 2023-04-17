package org.phyloviz.pwp.shared.service.project.file.isolate_data;

import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.UploadIsolateDataOutput;
import org.springframework.web.multipart.MultipartFile;

public interface IsolateDataAccessService {

    UploadIsolateDataOutput uploadIsolateData(String projectId, MultipartFile multipartFile, String userId);

    IsolateDataMetadata getIsolateDataMetadata(String projectId, String isolateDataId, String userId);

    IsolateDataMetadata getIsolateDataMetadata(String isolateDataId);

    IsolateDataMetadata saveIsolateDataMetadata(IsolateDataMetadata isolateData);

    void deleteIsolateData(String isolateDataId);

    void assertExists(String projectId, String isolateDataId, String userId);

    GetIsolateDataSchemaOutput getIsolateDataSchema(String projectId, String isolateDataId, String userId);

    GetIsolateDataRowsOutput getIsolateDataRows(String projectId, String isolateDataId, int limit, int offset,
                                                String userId);
}
