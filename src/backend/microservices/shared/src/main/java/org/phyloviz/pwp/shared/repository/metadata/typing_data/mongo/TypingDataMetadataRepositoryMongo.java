package org.phyloviz.pwp.shared.repository.metadata.typing_data.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.TypingDataMetadata;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public Optional<TypingDataMetadata> findByTypingDataId(String typingDataId) {
        return typingDataMetadataMongoRepository.findByTypingDataId(typingDataId);
    }

    @Override
    public List<TypingDataMetadata> findAllByTypingDataId(String typingDataId) {
        return typingDataMetadataMongoRepository.findAllByTypingDataId(typingDataId);
    }
}
