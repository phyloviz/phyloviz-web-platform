package org.phyloviz.pwp.repository.metadata.dataset.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.repository.metadata.dataset.documents.Dataset;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DatasetRepositoryMongo implements DatasetRepository {

    private final DatasetMongoRepository datasetMongoRepository;

    @Override
    public Dataset save(Dataset dataset) {
        return datasetMongoRepository.save(dataset);
    }

    @Override
    public void delete(Dataset dataset) {
        datasetMongoRepository.delete(dataset);
    }

    @Override
    public Optional<Dataset> findById(String resourceId) {
        return datasetMongoRepository.findById(resourceId);
    }

    @Override
    public List<Dataset> findAllByProjectId(String projectId) {
        return datasetMongoRepository.findAllByProjectId(projectId);
    }

    @Override
    public Optional<Dataset> findByProjectIdAndId(String projectId, String datasetId) {
        return datasetMongoRepository.findByProjectIdAndId(projectId, datasetId);
    }

    @Override
    public boolean existsByProjectIdAndId(String projectId, String datasetId) {
        return datasetMongoRepository.existsByProjectIdAndId(projectId, datasetId);
    }

    @Override
    public boolean existsByProjectIdAndTypingDataId(String projectId, String typingDataId) {
        return datasetMongoRepository.existsByProjectIdAndTypingDataId(projectId, typingDataId);
    }

    @Override
    public boolean existsByProjectIdAndIsolateDataId(String projectId, String isolateDataId) {
        return datasetMongoRepository.existsByProjectIdAndIsolateDataId(projectId, isolateDataId);
    }
}
