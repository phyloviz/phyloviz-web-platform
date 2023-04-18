package org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.adapters.typing_data.TypingDataAdapterId;
import org.phyloviz.pwp.shared.adapters.typing_data.TypingDataAdapterRegistry;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.specific_data.TypingDataAdapterSpecificData;
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

    private final TypingDataAdapterRegistry typingDataAdapterRegistry;

    @Override
    public TypingDataMetadata convert(@NotNull Document document) {
        try {
            TypingDataAdapterId adapterId = TypingDataAdapterId.valueOf(
                    document.getString("adapterId").toUpperCase()
            );

            Class<? extends TypingDataAdapterSpecificData> adapterSpecificDataClass =
                    typingDataAdapterRegistry.getTypingDataAdapterSpecificDataClass(adapterId);

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
            throw new DocumentConversionException("Error converting Document to TypingDataMetadata: " + e);
        }
    }
}
