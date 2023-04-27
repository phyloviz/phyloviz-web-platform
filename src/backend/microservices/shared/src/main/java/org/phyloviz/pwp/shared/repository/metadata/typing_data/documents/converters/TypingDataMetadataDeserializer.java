package org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.data.registry.typing_data.TypingDataDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.data.typing_data.TypingDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.specific_data.TypingDataDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.metadata.DocumentConversionException;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.TypingDataMetadata;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;

@ReadingConverter
@RequiredArgsConstructor
public class TypingDataMetadataDeserializer implements Converter<Document, TypingDataMetadata> {
    private final MongoConverter mongoConverter;

    private final TypingDataDataRepositoryRegistry typingDataDataRepositoryRegistry;

    @Override
    public TypingDataMetadata convert(@NotNull Document document) {
        try {
            TypingDataDataRepositoryId repositoryId = TypingDataDataRepositoryId.valueOf(
                    document.getString("repositoryId").toUpperCase()
            );

            Class<? extends TypingDataDataRepositorySpecificData> repositorySpecificDataClass =
                    typingDataDataRepositoryRegistry.getRepositorySpecificDataClass(repositoryId);

            Document repositorySpecificDataDocument = (Document) document.get("repositorySpecificData");
            TypingDataDataRepositorySpecificData repositorySpecificData = mongoConverter.read(repositorySpecificDataClass, repositorySpecificDataDocument);

            return new TypingDataMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("typingDataId"),
                    document.getString("name"),
                    repositoryId,
                    repositorySpecificData
            );
        } catch (Exception e) {
            throw new DocumentConversionException("Error converting Document to TypingDataMetadata: " + e);
        }
    }
}
