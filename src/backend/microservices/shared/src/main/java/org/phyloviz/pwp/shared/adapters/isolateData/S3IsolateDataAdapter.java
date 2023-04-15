package org.phyloviz.pwp.shared.adapters.isolateData;

import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataAdapterSpecificData;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class S3IsolateDataAdapter implements IsolateDataAdapter {

    @Override
    public String uploadIsolateData(String projectId, String isolateDataId, MultipartFile multipartFile) {
        String location = projectId + "/isolate-data/" + isolateDataId;

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String getIsolateData(IsolateDataAdapterSpecificData isolateDataAdapterSpecificData) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean isFileAdapter() {
        return true;
    }

    @Override
    public void deleteIsolateData(IsolateDataAdapterSpecificData isolateDataAdapterSpecificData) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
