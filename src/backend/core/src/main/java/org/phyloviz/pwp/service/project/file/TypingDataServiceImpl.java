package org.phyloviz.pwp.service.project.file;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.service.dtos.files.typing_data.TypingDataInfo;
import org.phyloviz.pwp.service.dtos.files.typing_data.UpdateTypingDataOutput;
import org.phyloviz.pwp.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.repository.data.registry.typing_data.TypingDataDataRepositoryFactory;
import org.phyloviz.pwp.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.repository.metadata.typing_data.TypingDataMetadataRepository;
import org.phyloviz.pwp.repository.metadata.typing_data.documents.TypingDataMetadata;
import org.phyloviz.pwp.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.service.exceptions.TypingDataNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TypingDataServiceImpl implements TypingDataService {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;

    private final TypingDataMetadataRepository typingDataMetadataRepository;

    private final TypingDataDataRepositoryFactory typingDataDataRepositoryFactory;

    @Override
    public List<TypingDataInfo> getTypingDataInfos(String projectId) {
        return typingDataMetadataRepository.findAllByProjectId(projectId).stream()
                .map(TypingDataInfo::new).toList();
    }

    @Override
    public void deleteTypingData(String projectId, String typingDataId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        TypingDataMetadata typingDataMetadata = typingDataMetadataRepository
                .findByProjectIdAndTypingDataId(projectId, typingDataId)
                .orElseThrow(TypingDataNotFoundException::new);

        if (datasetRepository.existsByProjectIdAndTypingDataId(projectId, typingDataId)) {
            throw new DeniedFileDeletionException(
                    "Cannot delete file. File is still being used in one or more datasets. Delete the datasets first."
            );
        }

        deleteTypingData(typingDataMetadata);
    }

    @Override
    public void deleteAllByProjectId(String projectId) {
        typingDataMetadataRepository.findAllByProjectId(projectId)
                .forEach(this::deleteTypingData);
    }

    @Override
    public UpdateTypingDataOutput updateTypingData(String name, String projectId, String typingDataId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        TypingDataMetadata typingDataMetadata = typingDataMetadataRepository
                .findByProjectIdAndTypingDataId(projectId, typingDataId)
                .orElseThrow(TypingDataNotFoundException::new);

        String previousName = typingDataMetadata.getName();

        if (name == null)
            throw new InvalidArgumentException("You have to provide at least one field to update");

        if (name.isBlank())
            throw new InvalidArgumentException("Name can't be blank");

        if (!name.equals(previousName)) {
            typingDataMetadata.setName(name);
            typingDataMetadataRepository.save(typingDataMetadata);
        }

        return new UpdateTypingDataOutput(previousName, name);
    }

    private void deleteTypingData(TypingDataMetadata typingDataMetadata) {
        typingDataMetadata.getRepositorySpecificData().forEach((repositoryId, repositorySpecificData) ->
                typingDataDataRepositoryFactory.getRepository(repositoryId)
                        .deleteTypingData(repositorySpecificData)
        );

        typingDataMetadataRepository.delete(typingDataMetadata);
    }
}
