package org.phyloviz.pwp.repository.metadata.typing_data.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.repository.metadata.DocumentConversionException;
import org.phyloviz.pwp.repository.metadata.typing_data.documents.TypingDataMetadata;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@WritingConverter
@RequiredArgsConstructor
public class TypingDataMetadataSerializer implements Converter<TypingDataMetadata, Document> {
    private final MongoConverter mongoConverter;

    @Override
    public Document convert(@NotNull TypingDataMetadata typingDataMetadata) {
        try {
            return new Document(
                    Map.of(
                            "projectId", typingDataMetadata.getProjectId(),
                            "typingDataId", typingDataMetadata.getTypingDataId(),
                            "type", typingDataMetadata.getType(),
                            "name", typingDataMetadata.getName(),
                            "repositorySpecificData", Objects.requireNonNull(
                                    mongoConverter.convertToMongoType(
                                            typingDataMetadata.getRepositorySpecificData().entrySet().stream()
                                                    .collect(Collectors.toMap(
                                                            entry -> entry.getKey().name().toLowerCase(),
                                                            Map.Entry::getValue
                                                    ))
                                    )
                            )
                    )
            );
        } catch (Exception e) {
            throw new DocumentConversionException("Error converting TypingDataMetadata to Document: " + e);
        }
    }
}
