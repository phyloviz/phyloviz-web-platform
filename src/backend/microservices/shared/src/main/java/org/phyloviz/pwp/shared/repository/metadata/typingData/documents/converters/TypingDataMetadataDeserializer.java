package org.phyloviz.pwp.shared.repository.metadata.typingData.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData.TypingDataAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData.TypingDataAdapterSpecificData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;

@ReadingConverter
@RequiredArgsConstructor
public class TypingDataMetadataDeserializer implements Converter<Document, TypingDataMetadata> {
    private final MongoConverter mongoConverter;

    @Override
    public TypingDataMetadata convert(@NotNull Document document) {
        try {
            TypingDataAdapterId adapterId = TypingDataAdapterId.valueOf(
                    document.getString("adapterId").toUpperCase()
            );

            Class<? extends TypingDataAdapterSpecificData> adapterSpecificDataClass = adapterId.getAdapterSpecificDataClass();

            Document adapterSpecificDataDocument = (Document) document.get("adapterSpecificData");
            TypingDataAdapterSpecificData adapterSpecificData = mongoConverter.read(adapterSpecificDataClass, adapterSpecificDataDocument);

            return new TypingDataMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("typingDataId"),
                    document.getString("name"),
                    adapterId,
                    adapterSpecificData
            );
        } catch (Exception e) {
            throw new RuntimeException("Error converting Document to TypingDataMetadata", e);
        }
    }
}
