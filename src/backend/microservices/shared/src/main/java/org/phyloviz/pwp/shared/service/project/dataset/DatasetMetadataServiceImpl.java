package org.phyloviz.pwp.shared.service.project.dataset;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.project.ProjectMetadataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DatasetMetadataServiceImpl implements DatasetMetadataService {

    private final ProjectMetadataService projectMetadataService;

    private final DatasetRepository datasetRepository;

    @Override
    public Dataset getDataset(String projectId, String datasetId, String userId) {
        Project project = projectMetadataService.getProject(projectId, userId);

        if (!project.getDatasetIds().contains(datasetId))
            throw new DatasetNotFoundException();

        return datasetRepository.findById(datasetId).orElseThrow(DatasetNotFoundException::new);
    }

    @Override
    public Dataset getDataset(String datasetId) {
        return datasetRepository.findById(datasetId).orElseThrow(DatasetNotFoundException::new);
    }

    @Override
    public Dataset getDatasetOrNull(String datasetId) {
        return datasetRepository.findById(datasetId).orElse(null);
    }

    @Override
    public List<Dataset> getAllDatasetsByProjectId(String projectId) {
        return datasetRepository.findAllByProjectId(projectId);
    }

    @Override
    public Dataset saveDataset(Dataset dataset) {
        return datasetRepository.save(dataset);
    }

    @Override
    public void deleteDataset(String datasetId) {
        Dataset dataset = getDataset(datasetId);
        datasetRepository.delete(dataset);
    }

    @Override
    public void assertExists(String projectId, String datasetId, String userId) {
        getDataset(projectId, datasetId, userId);
    }
}
