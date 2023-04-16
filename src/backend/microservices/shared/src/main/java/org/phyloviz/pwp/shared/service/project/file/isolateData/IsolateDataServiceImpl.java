package org.phyloviz.pwp.shared.service.project.file.isolateData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataS3AdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.adapters.isolateData.IsolateDataAdapterFactory;
import org.phyloviz.pwp.shared.service.dtos.files.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.shared.service.dtos.files.GetIsolateDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.IsolateDataMetadataDTO;
import org.phyloviz.pwp.shared.service.dtos.files.UploadIsolateDataOutput;
import org.phyloviz.pwp.shared.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.shared.service.exceptions.IsolateDataNotFoundException;
import org.phyloviz.pwp.shared.service.project.ProjectService;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IsolateDataServiceImpl implements IsolateDataService {

    private final IsolateDataMetadataRepository isolateDataMetadataRepository;
    private final IsolateDataAdapterFactory isolateDataAdapterFactory;

    private final ProjectService projectService;
    private final DatasetService datasetService;

    @Value("${adapters.upload-isolate-data-adapter}")
    private final IsolateDataAdapterId uploadIsolateDataAdapter;

    @Override
    public UploadIsolateDataOutput uploadIsolateData(String projectId, MultipartFile multipartFile, String userId) {
        Project project = projectService.getProject(projectId, userId);

        String isolateDataId = UUID.randomUUID().toString();

        String url = isolateDataAdapterFactory.getIsolateDataAdapter(uploadIsolateDataAdapter)
                .uploadIsolateData(projectId, isolateDataId, multipartFile);

        IsolateDataMetadata isolateDataMetadata = new IsolateDataMetadata(
                projectId,
                isolateDataId,
                multipartFile.getOriginalFilename(),
                uploadIsolateDataAdapter,
                switch (uploadIsolateDataAdapter) {
                    case S3 -> new IsolateDataS3AdapterSpecificData(url, multipartFile.getOriginalFilename());
                }
        );

        isolateDataMetadataRepository.save(isolateDataMetadata);

        project.getFileIds().getIsolateDataIds().add(isolateDataId);
        projectService.saveProject(project);

        return new UploadIsolateDataOutput(projectId, isolateDataId);
    }

    @Override
    public IsolateDataMetadata getIsolateDataMetadata(String projectId, String isolateDataId, String userId) {
        Project project = projectService.getProject(projectId, userId);

        if (!project.getFileIds().getIsolateDataIds().contains(isolateDataId))
            throw new IsolateDataNotFoundException();

        return isolateDataMetadataRepository.findByIsolateDataId(isolateDataId).orElseThrow(IsolateDataNotFoundException::new);
    }

    @Override
    public IsolateDataMetadata getIsolateDataMetadata(String isolateDataId) {
        return isolateDataMetadataRepository.findByIsolateDataId(isolateDataId).orElseThrow(IsolateDataNotFoundException::new);
    }

    @Override
    public IsolateDataMetadataDTO getIsolateDataMetadataDTO(String isolateDataId) {
        return new IsolateDataMetadataDTO(getIsolateDataMetadata(isolateDataId));
    }

    @Override
    public void assertExists(String projectId, String isolateDataId, String userId) {
        getIsolateDataMetadata(projectId, isolateDataId, userId);
    }

    @Override
    public IsolateDataMetadata saveIsolateDataMetadata(IsolateDataMetadata isolateData) {
        return isolateDataMetadataRepository.save(isolateData);
    }

    @Override
    public void deleteIsolateData(String projectId, String isolateDataId, String userId) {
        assertExists(projectId, isolateDataId, userId);

        Project project = projectService.getProject(projectId, userId);

        project.getDatasetIds().forEach(datasetId -> {
            Dataset dataset = datasetService.getDatasetOrNull(datasetId);

            if (dataset != null && dataset.getIsolateDataId().equals(isolateDataId))
                throw new DeniedFileDeletionException(
                        "Cannot delete file. File is still being used in one or more datasets. " +
                                "Delete the datasets first."
                );
        });

        deleteIsolateData(isolateDataId);

        project.getFileIds().getIsolateDataIds().remove(isolateDataId);
        projectService.saveProject(project);
    }

    @Override
    public void deleteIsolateData(String isolateDataId) {
        isolateDataMetadataRepository.findAllByIsolateDataId(isolateDataId)
                .forEach(isolateDataMetadata -> {
                    isolateDataAdapterFactory.getIsolateDataAdapter(isolateDataMetadata.getAdapterId())
                            .deleteIsolateData(isolateDataMetadata.getAdapterSpecificData());

                    isolateDataMetadataRepository.delete(isolateDataMetadata);
                });
    }

    @Override
    public GetIsolateDataSchemaOutput getIsolateDataSchema(String projectId, String isolateDataId, String userId) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public GetIsolateDataRowsOutput getIsolateDataRows(String projectId, String isolateDataId, int limit, int offset, String userId) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
