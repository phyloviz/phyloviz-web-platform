package org.phyloviz.pwp.shared.repository.metadata.isolate_data.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class IsolateDataMetadataRepositoryMongo implements IsolateDataMetadataRepository {

    private final IsolateDataMetadataMongoRepository isolateDataMetadataMongoRepository;

    @Override
    public IsolateDataMetadata save(IsolateDataMetadata isolateDataMetadata) {
        return isolateDataMetadataMongoRepository.save(isolateDataMetadata);
    }

    @Override
    public void delete(IsolateDataMetadata isolateDataMetadata) {
        isolateDataMetadataMongoRepository.delete(isolateDataMetadata);
    }

    @Override
    public Optional<IsolateDataMetadata> findByIsolateDataId(String isolateDataId) {
        return isolateDataMetadataMongoRepository.findByIsolateDataId(isolateDataId);
    }

    @Override
    public List<IsolateDataMetadata> findAllByIsolateDataId(String isolateDataId) {
        return isolateDataMetadataMongoRepository.findAllByIsolateDataId(isolateDataId);
    }
}
