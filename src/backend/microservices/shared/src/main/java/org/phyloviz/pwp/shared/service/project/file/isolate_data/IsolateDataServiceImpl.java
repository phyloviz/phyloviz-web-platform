package org.phyloviz.pwp.shared.service.project.file.isolate_data;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.IsolateDataInfo;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.UploadIsolateDataOutput;
import org.phyloviz.pwp.shared.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.shared.service.project.ProjectAccessService;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetAccessService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class IsolateDataServiceImpl implements IsolateDataService {

    private final ProjectAccessService projectAccessService;
    private final IsolateDataAccessService isolateDataAccessService;
    private final DatasetAccessService datasetAccessService;

    @Override
    public UploadIsolateDataOutput uploadIsolateData(String projectId, MultipartFile multipartFile, String userId) {
        return isolateDataAccessService.uploadIsolateData(projectId, multipartFile, userId);
    }

    @Override
    public IsolateDataMetadata getIsolateDataMetadata(String isolateDataId) {
        return isolateDataAccessService.getIsolateDataMetadata(isolateDataId);
    }

    @Override
    public IsolateDataInfo getIsolateDataInfo(String isolateDataId) {
        return new IsolateDataInfo(getIsolateDataMetadata(isolateDataId));
    }

    @Override
    public void deleteIsolateData(String projectId, String isolateDataId, String userId) {
        isolateDataAccessService.assertExists(projectId, isolateDataId, userId);

        Project project = projectAccessService.getProject(projectId, userId);

        project.getDatasetIds().forEach(datasetId -> {
            Dataset dataset = datasetAccessService.getDatasetOrNull(datasetId);

            if (dataset != null && dataset.getIsolateDataId() != null && dataset.getIsolateDataId().equals(isolateDataId))
                throw new DeniedFileDeletionException(
                        "Cannot delete file. File is still being used in one or more datasets. " +
                                "Delete the datasets first."
                );
        });

        deleteIsolateData(isolateDataId);

        project.getFileIds().getIsolateDataIds().remove(isolateDataId);
        projectAccessService.saveProject(project);
    }

    @Override
    public void deleteIsolateData(String isolateDataId) {
        isolateDataAccessService.deleteIsolateData(isolateDataId);
    }

    @Override
    public GetIsolateDataSchemaOutput getIsolateDataSchema(String projectId, String isolateDataId, String userId) {
        return isolateDataAccessService.getIsolateDataSchema(projectId, isolateDataId, userId);
    }

    @Override
    public GetIsolateDataRowsOutput getIsolateDataRows(String projectId, String isolateDataId, int limit, int offset, String userId) {
        return isolateDataAccessService.getIsolateDataRows(projectId, isolateDataId, limit, offset, userId);
    }
}
