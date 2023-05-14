package org.phyloviz.pwp.shared.repository.data.isolate_data.repository;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.S3FileRepository;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataS3DataRepositorySpecificData;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataSchemaOutput;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
@RequiredArgsConstructor
public class IsolateDataS3DataRepository implements IsolateDataDataRepository {

    private final S3FileRepository s3FileRepository;

    @Override
    public IsolateDataDataRepositorySpecificData uploadIsolateData(String projectId, String isolateDataId, MultipartFile multipartFile) {
        String url = projectId + "/isolate-data/" + isolateDataId;

        s3FileRepository.upload(url, multipartFile);

        return new IsolateDataS3DataRepositorySpecificData(s3FileRepository.getLocation() + "/" + url, multipartFile.getOriginalFilename());
    }

    @Override
    public String downloadIsolateData(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData) {
        IsolateDataS3DataRepositorySpecificData repositorySpecificData =
                (IsolateDataS3DataRepositorySpecificData) isolateDataDataRepositorySpecificData;

        return s3FileRepository.download(repositorySpecificData.getUrl());
    }

    @Override
    public GetIsolateDataSchemaOutput getIsolateDataSchema(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public GetIsolateDataRowsOutput getIsolateDataRows(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData, int limit, int offset) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void deleteIsolateData(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData) {
        IsolateDataS3DataRepositorySpecificData isolateDataS3DataRepositorySpecificData =
                (IsolateDataS3DataRepositorySpecificData) isolateDataDataRepositorySpecificData;

        s3FileRepository.delete(isolateDataS3DataRepositorySpecificData.getUrl());
    }
}
