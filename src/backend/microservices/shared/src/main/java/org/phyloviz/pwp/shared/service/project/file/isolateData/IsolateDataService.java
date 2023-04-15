package org.phyloviz.pwp.shared.service.project.file.isolateData;

import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.service.dtos.files.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.shared.service.dtos.files.GetIsolateDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.IsolateDataMetadataDTO;
import org.phyloviz.pwp.shared.service.dtos.files.UploadIsolateDataOutput;
import org.springframework.web.multipart.MultipartFile;

public interface IsolateDataService {

    UploadIsolateDataOutput uploadIsolateData(String projectId, MultipartFile multipartFile, String userId);

    IsolateDataMetadata getIsolateDataMetadata(String projectId, String isolateDataId, String userId);

    IsolateDataMetadata getIsolateDataMetadata(String isolateDataId);

    IsolateDataMetadataDTO getIsolateDataMetadataDTO(String isolateDataId);

    void assertExists(String projectId, String isolateDataId, String userId);

    IsolateDataMetadata saveIsolateDataMetadata(IsolateDataMetadata isolateData);

    void deleteIsolateData(String projectId, String isolateDataId, String userId);

    void deleteIsolateData(String isolateDataId);

    GetIsolateDataSchemaOutput getIsolateDataSchema(String projectId, String isolateDataId, String userId);

    GetIsolateDataRowsOutput getIsolateDataRows(String projectId, String isolateDataId, int limit, int offset,
                                                String userId);
}
