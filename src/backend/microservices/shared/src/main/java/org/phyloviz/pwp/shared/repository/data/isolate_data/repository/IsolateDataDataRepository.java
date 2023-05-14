package org.phyloviz.pwp.shared.repository.data.isolate_data.repository;

import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataSchemaOutput;
import org.springframework.web.multipart.MultipartFile;

public interface IsolateDataDataRepository {

    IsolateDataDataRepositorySpecificData uploadIsolateData(String projectId, String isolateDataId, MultipartFile multipartFile);

    String downloadIsolateData(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData);

    GetIsolateDataSchemaOutput getIsolateDataSchema(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData);

    GetIsolateDataRowsOutput getIsolateDataRows(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData,
                                                int limit, int offset);

    void deleteIsolateData(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData);
}
