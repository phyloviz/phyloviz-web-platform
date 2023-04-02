package org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.deserializer;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.IsolateDataAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.IsolateDataS3AdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataS3AdapterSpecificData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;

@ReadingConverter
@RequiredArgsConstructor
public class IsolateDataMetadataDeserializer implements Converter<Document, IsolateDataMetadata> {
    private final MongoConverter mongoConverter;

    @Override
    public IsolateDataMetadata convert(@NotNull Document document) {
        try {
            String adapterId = document.getString("adapterId");

            Class<? extends IsolateDataAdapterSpecificData> adapterSpecificDataClass;

            if (adapterId.equals("s3")) { // TODO change this
                adapterSpecificDataClass = IsolateDataS3AdapterSpecificData.class;
            } else {
                throw new RuntimeException("Unknown adapterId: " + adapterId);
            }

            Document adapterSpecificDataDocument = (Document) document.get("adapterSpecificData");
            IsolateDataAdapterSpecificData adapterSpecificData = mongoConverter.read(adapterSpecificDataClass, adapterSpecificDataDocument);

            return new IsolateDataMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("isolateDataId"),
                    document.getString("name"),
                    document.getString("url"),
                    document.getString("adapterId"),
                    adapterSpecificData
            );
        } catch (Exception e) {
            throw new RuntimeException("Error converting Document to IsolateDataAdapterSpecificData", e);
        }
    }
}
