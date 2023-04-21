package org.phyloviz.pwp.shared.adapters.isolate_data.adapter;

import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.specific_data.IsolateDataAdapterSpecificData;
import org.springframework.web.multipart.MultipartFile;

public interface IsolateDataAdapter {

    IsolateDataAdapterSpecificData uploadIsolateData(String projectId, String typingDataId, MultipartFile multipartFile);

    String getIsolateData(IsolateDataAdapterSpecificData isolateDataAdapterSpecificData);

    boolean isFileAdapter();

    void deleteIsolateData(IsolateDataAdapterSpecificData isolateDataAdapterSpecificData);
}
