package org.phyloviz.pwp.repository.data.isolate_data.repository;

import org.phyloviz.pwp.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;
import org.phyloviz.pwp.service.dtos.files.isolate_data.GetIsolateDataRowsOutput;
import org.springframework.web.multipart.MultipartFile;

public interface IsolateDataDataRepository {

    IsolateDataDataRepositorySpecificData uploadIsolateData(String projectId, String isolateDataId, MultipartFile multipartFile);

    String downloadIsolateData(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData);

    GetIsolateDataRowsOutput getIsolateDataRows(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData,
                                                int limit, int offset);

    void deleteIsolateData(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData);
}
