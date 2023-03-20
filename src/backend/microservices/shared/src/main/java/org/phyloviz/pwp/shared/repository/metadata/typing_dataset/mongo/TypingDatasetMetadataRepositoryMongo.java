package org.phyloviz.pwp.shared.repository.metadata.typing_dataset.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.typing_dataset.TypingDatasetMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_dataset.documents.TypingDatasetMetadata;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
@RequiredArgsConstructor
public class TypingDatasetMetadataRepositoryMongo implements TypingDatasetMetadataRepository {

    private final TypingDatasetMetadataMongoRepository typingDatasetMetadataMongoRepository;

    @Override
    public TypingDatasetMetadata save(TypingDatasetMetadata typingDatasetMetadata) {
        return typingDatasetMetadataMongoRepository.save(typingDatasetMetadata);
    }

    @Override
    public void delete(TypingDatasetMetadata typingDatasetMetadata) {
        typingDatasetMetadataMongoRepository.delete(typingDatasetMetadata);
    }

    @Override
    public TypingDatasetMetadata findByResourceId(String resourceId) {
        return typingDatasetMetadataMongoRepository.findByResourceId(resourceId);
    }
}
