package org.phyloviz.pwp.shared.service.project.file.isolate_data;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.isolate_data.IsolateDataAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.exceptions.IsolateDataNotFoundException;
import org.phyloviz.pwp.shared.service.project.ProjectMetadataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IsolateDataMetadataServiceImpl implements IsolateDataMetadataService {

    private final ProjectMetadataService projectMetadataService;

    private final IsolateDataMetadataRepository isolateDataMetadataRepository;

    @Override
    public IsolateDataMetadata getIsolateDataMetadata(String projectId, String isolateDataId, String userId) {
        Project project = projectMetadataService.getProject(projectId, userId);

        if (!project.getFileIds().getIsolateDataIds().contains(isolateDataId))
            throw new IsolateDataNotFoundException();

        return isolateDataMetadataRepository.findByIsolateDataId(isolateDataId).orElseThrow(IsolateDataNotFoundException::new);
    }

    @Override
    public IsolateDataMetadata getIsolateDataMetadata(String isolateDataId) {
        return isolateDataMetadataRepository.findByIsolateDataId(isolateDataId).orElseThrow(IsolateDataNotFoundException::new);
    }

    @Override
    public IsolateDataMetadata getIsolateDataMetadataByAdapterIdOrNull(String isolateDataId, IsolateDataAdapterId adapterId) {
        return isolateDataMetadataRepository
                .findByIsolateDataIdAndAdapterId(isolateDataId, adapterId)
                .orElse(null);
    }

    @Override
    public IsolateDataMetadata saveIsolateDataMetadata(IsolateDataMetadata isolateData) {
        return isolateDataMetadataRepository.save(isolateData);
    }

    @Override
    public List<IsolateDataMetadata> getAllIsolateDataMetadata(String isolateDataId) {
        return isolateDataMetadataRepository.findAllByIsolateDataId(isolateDataId);
    }

    @Override
    public List<IsolateDataMetadata> getAllIsolateDataMetadataByProjectId(String datasetId) {
        return isolateDataMetadataRepository.findAllByProjectId(datasetId);
    }

    @Override
    public void deleteIsolateData(IsolateDataMetadata isolateDataMetadata) {
        isolateDataMetadataRepository.delete(isolateDataMetadata);
    }

    @Override
    public void assertExists(String projectId, String isolateDataId, String userId) {
        getIsolateDataMetadata(projectId, isolateDataId, userId);
    }
}