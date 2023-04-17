package org.phyloviz.pwp.shared.service.project.file.typingData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.repository.metadata.typingData.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData.TypingDataAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData.TypingDataAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData.TypingDataS3AdapterSpecificData;
import org.phyloviz.pwp.shared.service.adapters.typingData.TypingDataAdapterFactory;
import org.phyloviz.pwp.shared.service.dtos.files.GetTypingDataProfilesOutput;
import org.phyloviz.pwp.shared.service.dtos.files.GetTypingDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.UploadTypingDataOutput;
import org.phyloviz.pwp.shared.service.exceptions.TypingDataNotFoundException;
import org.phyloviz.pwp.shared.service.project.ProjectAccessService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TypingDataAccessServiceImpl implements TypingDataAccessService {

    private final ProjectAccessService projectAccessService;

    private final TypingDataMetadataRepository typingDataMetadataRepository;
    private final TypingDataAdapterFactory typingDataAdapterFactory;

    @Value("${adapters.upload-typing-data-adapter}")
    private TypingDataAdapterId uploadTypingDataAdapter;

    @Override
    public UploadTypingDataOutput uploadTypingData(String projectId, MultipartFile multipartFile, String userId) {
        Project project = projectAccessService.getProject(projectId, userId);

        String typingDataId = UUID.randomUUID().toString();

        TypingDataAdapterSpecificData typingDataAdapterSpecificData = typingDataAdapterFactory
                .getTypingDataAdapter(uploadTypingDataAdapter)
                .uploadTypingData(projectId, typingDataId, multipartFile);

        TypingDataMetadata typingDataMetadata = new TypingDataMetadata(
                projectId,
                typingDataId,
                multipartFile.getOriginalFilename(),
                uploadTypingDataAdapter,
                typingDataAdapterSpecificData
        );

        typingDataMetadataRepository.save(typingDataMetadata);

        project.getFileIds().getTypingDataIds().add(typingDataId);
        projectAccessService.saveProject(project);

        return new UploadTypingDataOutput(projectId, typingDataId);
    }

    @Override
    public TypingDataMetadata getTypingDataMetadata(String projectId, String typingDataId, String userId) {
        Project project = projectAccessService.getProject(projectId, userId);

        if (!project.getFileIds().getTypingDataIds().contains(typingDataId))
            throw new TypingDataNotFoundException();

        return typingDataMetadataRepository.findByTypingDataId(typingDataId).orElseThrow(TypingDataNotFoundException::new);
    }

    @Override
    public TypingDataMetadata getTypingDataMetadata(String typingDataId) {
        return typingDataMetadataRepository.findByTypingDataId(typingDataId).orElseThrow(TypingDataNotFoundException::new);
    }

    @Override
    public TypingDataMetadata saveTypingDataMetadata(TypingDataMetadata typingData) {
        return typingDataMetadataRepository.save(typingData);
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
    public void assertExists(String projectId, String typingDataId, String userId) {
        getTypingDataMetadata(projectId, typingDataId, userId);
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
