package org.phyloviz.pwp.shared.repository.data.isolate_data.repository;

import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;
import org.springframework.web.multipart.MultipartFile;

public interface IsolateDataDataRepository {

    IsolateDataDataRepositorySpecificData uploadIsolateData(String projectId, String typingDataId, MultipartFile multipartFile);

    String getIsolateData(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData);

    boolean isFileRepository();

    void deleteIsolateData(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData);
}
