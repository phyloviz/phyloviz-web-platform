package org.phyloviz.pwp.shared.service.adapters.isolateData;

import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataAdapterSpecificData;
import org.springframework.web.multipart.MultipartFile;

public interface IsolateDataAdapter {

    String uploadIsolateData(String projectId, String typingDataId, MultipartFile multipartFile);

    String getIsolateData(IsolateDataAdapterSpecificData isolateDataAdapterSpecificData);

    boolean isFileAdapter();

    void deleteIsolateData(IsolateDataAdapterSpecificData isolateDataAdapterSpecificData);
}
