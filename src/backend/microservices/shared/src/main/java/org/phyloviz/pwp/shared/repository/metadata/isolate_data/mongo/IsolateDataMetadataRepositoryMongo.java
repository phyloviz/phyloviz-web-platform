package org.phyloviz.pwp.shared.repository.metadata.isolate_data.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Optional<IsolateDataMetadata> findByProjectIdAndIsolateDataId(String projectId, String isolateDataId) {
        return isolateDataMetadataMongoRepository.findByProjectIdAndIsolateDataId(projectId, isolateDataId);
    }

    @Override
    public List<IsolateDataMetadata> findAllByProjectId(String projectId) {
        Set<String> seenIsolateDataIds = new HashSet<>();

        return isolateDataMetadataMongoRepository.findAllByProjectId(projectId).stream()
                .filter((isolateDataMetadata -> {
                    if (seenIsolateDataIds.contains(isolateDataMetadata.getIsolateDataId())) {
                        return false;
                    }
                    seenIsolateDataIds.add(isolateDataMetadata.getIsolateDataId());
                    return true;
                }))
                .toList();
    }

    @Override
    public void delete(IsolateDataMetadata isolateDataMetadata) {
        isolateDataMetadataMongoRepository.delete(isolateDataMetadata);
    }

    @Override
    public boolean existsByProjectIdAndIsolateDataId(String projectId, String isolateDataId) {
        return isolateDataMetadataMongoRepository.existsByProjectIdAndIsolateDataId(projectId, isolateDataId);
    }
}
