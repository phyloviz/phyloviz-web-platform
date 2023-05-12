package org.phyloviz.pwp.shared.repository.data.isolate_data.repository;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.S3FileRepository;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataS3DataRepositorySpecificData;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
@RequiredArgsConstructor
public class IsolateDataS3DataRepository implements IsolateDataDataRepository {

    private final S3FileRepository s3FileRepository;

    @Override
    public IsolateDataDataRepositorySpecificData uploadIsolateData(String projectId, String isolateDataId, MultipartFile multipartFile) {
        String url = projectId + "/isolate-data/" + isolateDataId;

        s3FileRepository.store(url, multipartFile);

        return new IsolateDataS3DataRepositorySpecificData(s3FileRepository.getLocation() + "/" + url, multipartFile.getOriginalFilename());
    }

    @Override
    public String getIsolateData(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteIsolateData(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData) {
        IsolateDataS3DataRepositorySpecificData isolateDataS3DataRepositorySpecificData =
                (IsolateDataS3DataRepositorySpecificData) isolateDataDataRepositorySpecificData;

        s3FileRepository.delete(isolateDataS3DataRepositorySpecificData.getUrl());
    }

}
