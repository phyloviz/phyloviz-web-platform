package org.phyloviz.pwp.shared.service.project.file.isolateData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataS3AdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.dtos.files.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.shared.service.dtos.files.GetIsolateDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.IsolateDataMetadataDTO;
import org.phyloviz.pwp.shared.service.dtos.files.UploadIsolateDataOutput;
import org.phyloviz.pwp.shared.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.shared.service.project.ProjectAccessService;
import org.phyloviz.pwp.shared.service.project.ProjectService;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetAccessService;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

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
    public IsolateDataMetadata getIsolateDataMetadata(String projectId, String isolateDataId, String userId) {
        return isolateDataAccessService.getIsolateDataMetadata(projectId, isolateDataId, userId);
    }

    @Override
    public IsolateDataMetadata getIsolateDataMetadata(String isolateDataId) {
        return isolateDataAccessService.getIsolateDataMetadata(isolateDataId);
    }

    @Override
    public IsolateDataMetadataDTO getIsolateDataMetadataDTO(String isolateDataId) {
        return new IsolateDataMetadataDTO(getIsolateDataMetadata(isolateDataId));
    }

    @Override
    public void assertExists(String projectId, String isolateDataId, String userId) {
        isolateDataAccessService.assertExists(projectId, isolateDataId, userId);
    }

    @Override
    public IsolateDataMetadata saveIsolateDataMetadata(IsolateDataMetadata isolateData) {
        return isolateDataAccessService.saveIsolateDataMetadata(isolateData);
    }

    @Override
    public void deleteIsolateData(String projectId, String isolateDataId, String userId) {
        assertExists(projectId, isolateDataId, userId);

        Project project = projectAccessService.getProject(projectId, userId);

        project.getDatasetIds().forEach(datasetId -> {
            Dataset dataset = datasetAccessService.getDatasetOrNull(datasetId);

            if (dataset != null && dataset.getIsolateDataId().equals(isolateDataId))
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
