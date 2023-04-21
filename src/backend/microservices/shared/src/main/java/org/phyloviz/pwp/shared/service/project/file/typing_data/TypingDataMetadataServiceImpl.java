package org.phyloviz.pwp.shared.service.project.file.typing_data;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.typing_data.TypingDataAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.service.exceptions.TypingDataNotFoundException;
import org.phyloviz.pwp.shared.service.project.ProjectMetadataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypingDataMetadataServiceImpl implements TypingDataMetadataService {

    private final ProjectMetadataService projectMetadataService;

    private final TypingDataMetadataRepository typingDataMetadataRepository;

    @Override
    public TypingDataMetadata getTypingDataMetadata(String projectId, String typingDataId, String userId) {
        Project project = projectMetadataService.getProject(projectId, userId);

        if (!project.getFileIds().getTypingDataIds().contains(typingDataId))
            throw new TypingDataNotFoundException();

        return typingDataMetadataRepository.findByTypingDataId(typingDataId).orElseThrow(TypingDataNotFoundException::new);
    }

    @Override
    public TypingDataMetadata getTypingDataMetadata(String typingDataId) {
        return typingDataMetadataRepository.findByTypingDataId(typingDataId).orElseThrow(TypingDataNotFoundException::new);
    }

    @Override
    public TypingDataMetadata getTypingDataMetadataByAdapterIdOrNull(String typingDataId, TypingDataAdapterId adapterId) {
        return typingDataMetadataRepository
                .findByTypingDataIdAndAdapterId(typingDataId, adapterId)
                .orElse(null);
    }

    @Override
    public TypingDataMetadata saveTypingDataMetadata(TypingDataMetadata typingData) {
        return typingDataMetadataRepository.save(typingData);
    }

    @Override
    public List<TypingDataMetadata> getAllTypingDataMetadata(String typingDataId) {
        return typingDataMetadataRepository.findAllByTypingDataId(typingDataId);
    }

    @Override
    public List<TypingDataMetadata> getAllTypingDataMetadataByProjectId(String datasetId) {
        return typingDataMetadataRepository.findAllByProjectId(datasetId);
    }

    @Override
    public void deleteTypingData(TypingDataMetadata typingDataMetadata) {
        typingDataMetadataRepository.delete(typingDataMetadata);
    }

    @Override
    public void assertExists(String projectId, String typingDataId, String userId) {
        getTypingDataMetadata(projectId, typingDataId, userId);
    }
}
