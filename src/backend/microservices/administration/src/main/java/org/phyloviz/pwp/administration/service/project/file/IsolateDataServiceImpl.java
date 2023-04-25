package org.phyloviz.pwp.administration.service.project.file;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.service.dtos.files.isolate_data.UpdateIsolateDataOutput;
import org.phyloviz.pwp.shared.adapters.isolate_data.IsolateDataAdapterFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.administration.service.dtos.files.isolate_data.IsolateDataInfo;
import org.phyloviz.pwp.administration.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.IsolateDataNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IsolateDataServiceImpl implements IsolateDataService {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;

    private final IsolateDataMetadataRepository isolateDataMetadataRepository;

    private final IsolateDataAdapterFactory isolateDataAdapterFactory;

    @Override
    public List<IsolateDataInfo> getIsolateDataInfos(String projectId) {
        return isolateDataMetadataRepository.findAllByProjectId(projectId).stream()
                .map(IsolateDataInfo::new).toList();
    }

    @Override
    public void deleteIsolateData(String projectId, String isolateDataId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        if (!isolateDataMetadataRepository.existsByProjectIdAndIsolateDataId(projectId, isolateDataId))
            throw new IsolateDataNotFoundException();

        if (datasetRepository.existsByProjectIdAndIsolateDataId(projectId, isolateDataId)) {
            throw new DeniedFileDeletionException(
                    "Cannot delete file. File is still being used in one or more datasets. Delete the datasets first."
            );
        }

        deleteIsolateData(isolateDataId);
    }

    @Override
    public void deleteAllByProjectId(String projectId) {
        isolateDataMetadataRepository.findAllByProjectId(projectId)
                .forEach(this::deleteIsolateData);
    }

    @Override
    public void deleteIsolateData(String isolateDataId) {
        isolateDataMetadataRepository.findAllByIsolateDataId(isolateDataId)
                .forEach(this::deleteIsolateData);
    }

    @Override
    public UpdateIsolateDataOutput updateIsolateData(String name, String projectId, String isolateDataId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        if (!isolateDataMetadataRepository.existsByProjectIdAndIsolateDataId(projectId, isolateDataId))
            throw new IsolateDataNotFoundException();

        String previousName = isolateDataMetadataRepository.findAnyByProjectIdAndIsolateDataId(projectId, isolateDataId)
                .orElseThrow(IsolateDataNotFoundException::new)
                .getName();

        if (name == null)
            throw new InvalidArgumentException("You have to provide at least one field to update");

        if (name.equals(previousName))
            throw new InvalidArgumentException("The provided name is the same as the previous one");

        isolateDataMetadataRepository.findAllByProjectIdAndIsolateDataId(projectId, isolateDataId)
                .forEach(isolateDataMetadata -> {
                    if (!name.isBlank())
                        isolateDataMetadata.setName(name);

                    isolateDataMetadataRepository.save(isolateDataMetadata);
                });

        return new UpdateIsolateDataOutput(previousName, name);
    }

    private void deleteIsolateData(IsolateDataMetadata isolateDataMetadata) {
        isolateDataAdapterFactory.getIsolateDataAdapter(isolateDataMetadata.getAdapterId())
                .deleteIsolateData(isolateDataMetadata.getAdapterSpecificData());

        isolateDataMetadataRepository.delete(isolateDataMetadata);
    }
}
