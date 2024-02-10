package org.phyloviz.pwp.repository.metadata.typing_data.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.repository.data.registry.typing_data.TypingDataDataRepositoryRegistry;
import org.phyloviz.pwp.repository.data.typing_data.TypingDataDataRepositoryId;
import org.phyloviz.pwp.repository.data.typing_data.repository.specific_data.TypingDataDataRepositorySpecificData;
import org.phyloviz.pwp.repository.metadata.DocumentConversionException;
import org.phyloviz.pwp.repository.metadata.typing_data.documents.TypingDataMetadata;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@ReadingConverter
@RequiredArgsConstructor
public class TypingDataMetadataDeserializer implements Converter<Document, TypingDataMetadata> {
    private final MongoConverter mongoConverter;

    private final TypingDataDataRepositoryRegistry typingDataDataRepositoryRegistry;

    @Override
    public TypingDataMetadata convert(@NotNull Document document) {
        try {
            Map<String, Document> repositorySpecificDataMap1 =
                    document.get("repositorySpecificData", Map.class);

            Map<TypingDataDataRepositoryId, TypingDataDataRepositorySpecificData> repositorySpecificDataMap =
                    new HashMap<>();

            repositorySpecificDataMap1.forEach((repositoryIdString, repositorySpecificDataDocument) -> {
                TypingDataDataRepositoryId repositoryId = TypingDataDataRepositoryId.valueOf(
                        repositoryIdString.toUpperCase()
                );

                Class<? extends TypingDataDataRepositorySpecificData> repositorySpecificDataClass =
                        typingDataDataRepositoryRegistry.getRepositorySpecificDataClass(repositoryId);

                repositorySpecificDataMap.put(
                        repositoryId,
                        mongoConverter.read(repositorySpecificDataClass, repositorySpecificDataDocument)
                );
            });

            return new TypingDataMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("typingDataId"),
                    document.getString("type"),
                    document.getString("name"),
                    repositorySpecificDataMap
            );
        } catch (Exception e) {
            throw new DocumentConversionException("Error converting Document to TypingDataMetadata: " + e);
        }
    }
}
