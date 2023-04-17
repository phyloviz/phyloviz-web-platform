package org.phyloviz.pwp.shared.service.project.file.isolateData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataS3AdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.adapters.isolateData.IsolateDataAdapterFactory;
import org.phyloviz.pwp.shared.service.dtos.files.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.shared.service.dtos.files.GetIsolateDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.UploadIsolateDataOutput;
import org.phyloviz.pwp.shared.service.exceptions.IsolateDataNotFoundException;
import org.phyloviz.pwp.shared.service.project.ProjectAccessService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IsolateDataAccessServiceImpl implements IsolateDataAccessService {

    private final ProjectAccessService projectAccessService;

    private final IsolateDataMetadataRepository isolateDataMetadataRepository;
    private final IsolateDataAdapterFactory isolateDataAdapterFactory;

    @Value("${adapters.upload-isolate-data-adapter}")
    private IsolateDataAdapterId uploadIsolateDataAdapter;

    @Override
    public UploadIsolateDataOutput uploadIsolateData(String projectId, MultipartFile multipartFile, String userId) {
        Project project = projectAccessService.getProject(projectId, userId);

        String isolateDataId = UUID.randomUUID().toString();

        IsolateDataAdapterSpecificData isolateDataAdapterSpecificData = isolateDataAdapterFactory
                .getIsolateDataAdapter(uploadIsolateDataAdapter)
                .uploadIsolateData(projectId, isolateDataId, multipartFile);

        IsolateDataMetadata isolateDataMetadata = new IsolateDataMetadata(
                projectId,
                isolateDataId,
                multipartFile.getOriginalFilename(),
                uploadIsolateDataAdapter,
                isolateDataAdapterSpecificData
        );

        isolateDataMetadataRepository.save(isolateDataMetadata);

        project.getFileIds().getIsolateDataIds().add(isolateDataId);
        projectAccessService.saveProject(project);

        return new UploadIsolateDataOutput(projectId, isolateDataId);
    }

    @Override
    public IsolateDataMetadata getIsolateDataMetadata(String projectId, String isolateDataId, String userId) {
        Project project = projectAccessService.getProject(projectId, userId);

        if (!project.getFileIds().getIsolateDataIds().contains(isolateDataId))
            throw new IsolateDataNotFoundException();

        return isolateDataMetadataRepository.findByIsolateDataId(isolateDataId).orElseThrow(IsolateDataNotFoundException::new);
    }

    @Override
    public IsolateDataMetadata getIsolateDataMetadata(String isolateDataId) {
        return isolateDataMetadataRepository.findByIsolateDataId(isolateDataId).orElseThrow(IsolateDataNotFoundException::new);
    }

    @Override
    public IsolateDataMetadata saveIsolateDataMetadata(IsolateDataMetadata isolateData) {
        return isolateDataMetadataRepository.save(isolateData);
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
    public void assertExists(String projectId, String isolateDataId, String userId) {
        getIsolateDataMetadata(projectId, isolateDataId, userId);
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
