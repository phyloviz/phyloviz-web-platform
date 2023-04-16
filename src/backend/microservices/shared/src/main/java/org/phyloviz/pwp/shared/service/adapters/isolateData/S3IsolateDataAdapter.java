package org.phyloviz.pwp.shared.service.adapters.isolateData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.S3FileRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataS3AdapterSpecificData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3IsolateDataAdapter implements IsolateDataAdapter {

    private final S3FileRepository s3FileRepository;

    @Override
    public String uploadIsolateData(String projectId, String isolateDataId, MultipartFile multipartFile) {
        String url = projectId + "/isolate-data/" + isolateDataId;

        s3FileRepository.store(url, multipartFile);

        return s3FileRepository.getLocation() + "/" + url;
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
        IsolateDataS3AdapterSpecificData isolateDataS3AdapterSpecificData =
                (IsolateDataS3AdapterSpecificData) isolateDataAdapterSpecificData;

        s3FileRepository.delete(isolateDataS3AdapterSpecificData.getUrl());
    }

}
