package org.phyloviz.pwp.service.project.file;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.service.dtos.files.isolate_data.IsolateDataInfo;
import org.phyloviz.pwp.service.dtos.files.isolate_data.UpdateIsolateDataOutput;
import org.phyloviz.pwp.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.shared.repository.data.registry.isolate_data.IsolateDataDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.IsolateDataNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IsolateDataServiceImpl implements IsolateDataService {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;

    private final IsolateDataMetadataRepository isolateDataMetadataRepository;

    private final IsolateDataDataRepositoryFactory isolateDataDataRepositoryFactory;

    @Override
    public List<IsolateDataInfo> getIsolateDataInfos(String projectId) {
        return isolateDataMetadataRepository.findAllByProjectId(projectId).stream()
                .map(IsolateDataInfo::new).toList();
    }

    @Override
    public void deleteIsolateData(String projectId, String isolateDataId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        IsolateDataMetadata isolateDataMetadata = isolateDataMetadataRepository
                .findByProjectIdAndIsolateDataId(projectId, isolateDataId)
                .orElseThrow(IsolateDataNotFoundException::new);

        if (datasetRepository.existsByProjectIdAndIsolateDataId(projectId, isolateDataId)) {
            throw new DeniedFileDeletionException(
                    "Cannot delete file. File is still being used in one or more datasets. Delete the datasets first."
            );
        }

        deleteIsolateData(isolateDataMetadata);
    }

    @Override
    public void deleteAllByProjectId(String projectId) {
        isolateDataMetadataRepository.findAllByProjectId(projectId)
                .forEach(this::deleteIsolateData);
    }

    @Override
    public UpdateIsolateDataOutput updateIsolateData(String name, String projectId, String isolateDataId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        IsolateDataMetadata isolateDataMetadata = isolateDataMetadataRepository
                .findByProjectIdAndIsolateDataId(projectId, isolateDataId)
                .orElseThrow(IsolateDataNotFoundException::new);

        String previousName = isolateDataMetadata.getName();

        if (name == null)
            throw new InvalidArgumentException("You have to provide at least one field to update");

        if (name.isBlank())
            throw new InvalidArgumentException("Name can't be blank");

        if (!name.equals(previousName)) {
            isolateDataMetadata.setName(name);
            isolateDataMetadataRepository.save(isolateDataMetadata);
        }

        return new UpdateIsolateDataOutput(previousName, name);
    }

    private void deleteIsolateData(IsolateDataMetadata isolateDataMetadata) {
        isolateDataMetadata.getRepositorySpecificData().forEach((repositoryId, repositorySpecificData) ->
                isolateDataDataRepositoryFactory.getRepository(repositoryId)
                        .deleteIsolateData(repositorySpecificData)
        );

        isolateDataMetadataRepository.delete(isolateDataMetadata);
    }
}