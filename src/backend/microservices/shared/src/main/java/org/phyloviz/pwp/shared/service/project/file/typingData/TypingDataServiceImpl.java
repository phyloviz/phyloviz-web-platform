package org.phyloviz.pwp.shared.service.project.file.typingData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.typingData.TypingDataAdapterFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.repository.metadata.typingData.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData.TypingDataS3AdapterSpecificData;
import org.phyloviz.pwp.shared.service.dtos.files.GetTypingDataProfilesOutput;
import org.phyloviz.pwp.shared.service.dtos.files.GetTypingDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.TypingDataMetadataDTO;
import org.phyloviz.pwp.shared.service.dtos.files.UploadTypingDataOutput;
import org.phyloviz.pwp.shared.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.shared.service.exceptions.TypingDataNotFoundException;
import org.phyloviz.pwp.shared.service.project.ProjectService;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TypingDataServiceImpl implements TypingDataService {

    private final TypingDataMetadataRepository typingDataMetadataRepository;
    private final TypingDataAdapterFactory typingDataAdapterFactory;

    private final ProjectService projectService;
    private final DatasetService datasetService;

    @Override
    public UploadTypingDataOutput uploadTypingData(String projectId, MultipartFile multipartFile, String userId) {
        Project project = projectService.getProject(projectId, userId);

        String typingDataId = UUID.randomUUID().toString();

        String url = typingDataAdapterFactory.getTypingDataAdapter("s3")
                .uploadTypingData(projectId, typingDataId, multipartFile);

        final TypingDataMetadata typingDataMetadata = new TypingDataMetadata(
                projectId,
                typingDataId,
                multipartFile.getOriginalFilename(),
                "s3",
                new TypingDataS3AdapterSpecificData(url, multipartFile.getOriginalFilename())
        );

        typingDataMetadataRepository.save(typingDataMetadata);

        project.getFileIds().getTypingDataIds().add(typingDataId);
        projectService.saveProject(project);

        return new UploadTypingDataOutput(projectId, typingDataId);
    }

    @Override
    public TypingDataMetadata getTypingDataMetadata(String projectId, String typingDataId, String userId) {
        Project project = projectService.getProject(projectId, userId);

        if (!project.getFileIds().getTypingDataIds().contains(typingDataId))
            throw new TypingDataNotFoundException();

        return typingDataMetadataRepository.findByTypingDataId(typingDataId).orElseThrow(TypingDataNotFoundException::new);
    }

    @Override
    public TypingDataMetadata getTypingDataMetadata(String typingDataId) {
        return typingDataMetadataRepository.findByTypingDataId(typingDataId).orElseThrow(TypingDataNotFoundException::new);
    }

    @Override
    public TypingDataMetadataDTO getTypingDataMetadataDTO(String typingDataId) {
        return new TypingDataMetadataDTO(getTypingDataMetadata(typingDataId));
    }

    @Override
    public void assertExists(String projectId, String typingDataId, String userId) {
        getTypingDataMetadata(projectId, typingDataId, userId);
    }

    @Override
    public TypingDataMetadata saveTypingDataMetadata(TypingDataMetadata typingData) {
        return typingDataMetadataRepository.save(typingData);
    }

    @Override
    public void deleteTypingData(String projectId, String typingDataId, String userId) {
        assertExists(projectId, typingDataId, userId);

        Project project = projectService.getProject(projectId, userId);

        project.getDatasetIds().forEach(datasetId -> {
            Dataset dataset = datasetService.getDatasetOrNull(datasetId);

            if (dataset != null && dataset.getTypingDataId().equals(typingDataId))
                throw new DeniedFileDeletionException(
                        "Cannot delete file. File is still being used in one or more datasets. " +
                                "Delete the datasets first."
                );
        });

        deleteTypingData(typingDataId);

        project.getFileIds().getTypingDataIds().remove(typingDataId);
        projectService.saveProject(project);
    }

    @Override
    public void deleteTypingData(String typingDataId) {
        typingDataMetadataRepository.findAllByTypingDataId(typingDataId)
                .forEach(typingDataMetadata -> {
                    typingDataAdapterFactory.getTypingDataAdapter(typingDataMetadata.getAdapterId())
                            .deleteTypingData(typingDataMetadata.getAdapterSpecificData());

                    typingDataMetadataRepository.delete(typingDataMetadata);
                });
    }

    @Override
    public GetTypingDataSchemaOutput getTypingDataSchema(String projectId, String typingDataId, String userId) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public GetTypingDataProfilesOutput getTypingDataProfiles(String projectId, String typingDataId, int limit, int offset, String userId) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
