package org.phyloviz.pwp.shared.repository.metadata.typingData.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataMetadata;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Objects;

@WritingConverter
@RequiredArgsConstructor
public class TypingDataMetadataSerializer implements Converter<TypingDataMetadata, Document> {
    private final MongoConverter mongoConverter;

    @Override
    public Document convert(@NotNull TypingDataMetadata typingDataMetadata) {
        try {
            return new Document(
                    Map.of(
                            "_id", typingDataMetadata.getId(),
                            "projectId", typingDataMetadata.getProjectId(),
                            "typingDataId", typingDataMetadata.getTypingDataId(),
                            "name", typingDataMetadata.getName(),
                            "adapterId", typingDataMetadata.getAdapterId().name().toLowerCase(),
                            "adapterSpecificData", Objects.requireNonNull(
                                    mongoConverter.convertToMongoType(typingDataMetadata.getAdapterSpecificData())
                            )
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException("Error converting TypingDataMetadata to Document", e);
        }
    }
}
