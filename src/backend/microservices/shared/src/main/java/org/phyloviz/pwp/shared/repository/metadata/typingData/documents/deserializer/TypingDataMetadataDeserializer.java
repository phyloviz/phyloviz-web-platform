package org.phyloviz.pwp.shared.repository.metadata.typingData.documents.deserializer;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataS3AdapterSpecificData;
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
            String adapterId = document.getString("adapterId");

            Class<? extends TypingDataAdapterSpecificData> adapterSpecificDataClass;

            if (adapterId.equals("s3")) { // TODO change this
                adapterSpecificDataClass = TypingDataS3AdapterSpecificData.class;
            } else {
                throw new RuntimeException("Unknown adapterId: " + adapterId);
            }

            Document adapterSpecificDataDocument = (Document) document.get("adapterSpecificData");
            TypingDataAdapterSpecificData adapterSpecificData = mongoConverter.read(adapterSpecificDataClass, adapterSpecificDataDocument);

            return new TypingDataMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("typingDataId"),
                    document.getString("name"),
                    document.getString("url"),
                    document.getString("adapterId"),
                    adapterSpecificData
            );
        } catch (Exception e) {
            throw new RuntimeException("Error converting Document to TypingDataAdapterSpecificData", e);
        }
    }
}
