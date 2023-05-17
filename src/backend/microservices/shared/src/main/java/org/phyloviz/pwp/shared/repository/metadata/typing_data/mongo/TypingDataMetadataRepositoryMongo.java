package org.phyloviz.pwp.shared.repository.metadata.typing_data.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.TypingDataMetadataRepository;
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
public class TypingDataMetadataRepositoryMongo implements TypingDataMetadataRepository {

    private final TypingDataMetadataMongoRepository typingDataMetadataMongoRepository;

    @Override
    public TypingDataMetadata save(TypingDataMetadata typingDataMetadata) {
        return typingDataMetadataMongoRepository.save(typingDataMetadata);
    }

    @Override
    public Optional<TypingDataMetadata> findByProjectIdAndTypingDataId(String projectId, String typingDataId) {
        return typingDataMetadataMongoRepository.findByProjectIdAndTypingDataId(projectId, typingDataId);
    }

    @Override
    public List<TypingDataMetadata> findAllByProjectId(String projectId) {
        Set<String> seenTypingDataIds = new HashSet<>();

        return typingDataMetadataMongoRepository.findAllByProjectId(projectId).stream()
                .filter((typingDataMetadata -> {
                    if (seenTypingDataIds.contains(typingDataMetadata.getTypingDataId())) {
                        return false;
                    }
                    seenTypingDataIds.add(typingDataMetadata.getTypingDataId());
                    return true;
                }))
                .toList();
    }

    @Override
    public boolean existsByProjectIdAndTypingDataId(String projectId, String typingDataId) {
        return typingDataMetadataMongoRepository.existsByProjectIdAndTypingDataId(projectId, typingDataId);
    }

    @Override
    public void delete(TypingDataMetadata typingDataMetadata) {
        typingDataMetadataMongoRepository.delete(typingDataMetadata);
    }
}
