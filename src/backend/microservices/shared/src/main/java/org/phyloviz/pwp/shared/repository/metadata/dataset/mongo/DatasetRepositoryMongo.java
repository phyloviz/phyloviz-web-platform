package org.phyloviz.pwp.shared.repository.metadata.dataset.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.springframework.stereotype.Repository;

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
    public Dataset findById(String resourceId) {
        return datasetMongoRepository
                .findById(resourceId)
                .orElseThrow(DatasetNotFoundException::new);
    }
}
