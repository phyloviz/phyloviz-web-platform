package org.phyloviz.pwp.administration.service.project.file;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.typing_data.TypingDataAdapterFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.TypingDataInfo;
import org.phyloviz.pwp.shared.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TypingDataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypingDataServiceImpl implements TypingDataService {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;

    private final TypingDataMetadataRepository typingDataMetadataRepository;

    private final TypingDataAdapterFactory typingDataAdapterFactory;

    @Override
    public List<TypingDataInfo> getTypingDataInfos(String projectId) {
        return typingDataMetadataRepository.findAllByProjectId(projectId).stream()
                .map(TypingDataInfo::new).toList();
    }

    @Override
    public void deleteTypingData(String projectId, String typingDataId, String userId) {
        if(!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        if (!typingDataMetadataRepository.existsByProjectIdAndTypingDataId(projectId, typingDataId))
            throw new TypingDataNotFoundException();

        if (datasetRepository.existsByProjectIdAndTypingDataId(projectId, typingDataId)) {
            throw new DeniedFileDeletionException(
                    "Cannot delete file. File is still being used in one or more datasets. Delete the datasets first."
            );
        }

        deleteTypingData(typingDataId);
    }

    @Override
    public void deleteAllByProjectId(String projectId) {
        typingDataMetadataRepository.findAllByProjectId(projectId)
                .forEach(this::deleteTypingData);
    }

    @Override
    public void deleteTypingData(String typingDataId) {
        typingDataMetadataRepository.findAllByTypingDataId(typingDataId)
                .forEach(this::deleteTypingData);
    }

    private void deleteTypingData(TypingDataMetadata typingDataMetadata) {
        typingDataAdapterFactory.getTypingDataAdapter(typingDataMetadata.getAdapterId())
                .deleteTypingData(typingDataMetadata.getAdapterSpecificData());

        typingDataMetadataRepository.delete(typingDataMetadata);
    }
}
