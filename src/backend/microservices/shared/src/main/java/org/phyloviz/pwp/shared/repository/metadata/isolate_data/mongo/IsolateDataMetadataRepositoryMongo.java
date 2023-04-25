package org.phyloviz.pwp.shared.repository.metadata.isolate_data.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.isolate_data.IsolateDataAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.TypingDataMetadata;
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
    public Optional<IsolateDataMetadata> findAnyByProjectIdAndIsolateDataId(String projectId, String isolateDataId) {
        return isolateDataMetadataMongoRepository.findFirstByProjectIdAndIsolateDataId(projectId, isolateDataId);
    }

    @Override
    public List<IsolateDataMetadata> findAllByProjectIdAndIsolateDataId(String projectId, String isolateDataId) {
        return isolateDataMetadataMongoRepository.findAllByProjectIdAndIsolateDataId(projectId, isolateDataId);
    }

    @Override
    public List<IsolateDataMetadata> findAllByIsolateDataId(String isolateDataId) {
        return isolateDataMetadataMongoRepository.findAllByIsolateDataId(isolateDataId);
    }

    @Override
    public Optional<IsolateDataMetadata> findByIsolateDataIdAndAdapterId(String isolateDataId, IsolateDataAdapterId adapterId) {
        return isolateDataMetadataMongoRepository.findByIsolateDataIdAndAdapterId(
                isolateDataId, adapterId.name().toLowerCase()
        );
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
