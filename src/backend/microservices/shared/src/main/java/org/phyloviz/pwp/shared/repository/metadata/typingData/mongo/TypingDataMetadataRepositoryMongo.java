package org.phyloviz.pwp.shared.repository.metadata.typingData.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.typingData.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataMetadata;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public void delete(TypingDataMetadata typingDataMetadata) {
        typingDataMetadataMongoRepository.delete(typingDataMetadata);
    }

    @Override
    public TypingDataMetadata findByTypingDataId(String typingDataId) {
        return typingDataMetadataMongoRepository.findByTypingDataId(typingDataId);
    }

    @Override
    public List<TypingDataMetadata> findAllByTypingDataId(String typingDataId) {
        return typingDataMetadataMongoRepository.findAllByTypingDataId(typingDataId);
    }
}
