package org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataAdapterSpecificData;
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
            IsolateDataAdapterId adapterId = IsolateDataAdapterId.valueOf(
                    document.getString("adapterId").toUpperCase()
            );

            Class<? extends IsolateDataAdapterSpecificData> adapterSpecificDataClass = adapterId.getAdapterSpecificDataClass();

            Document adapterSpecificDataDocument = (Document) document.get("adapterSpecificData");
            IsolateDataAdapterSpecificData adapterSpecificData = mongoConverter.read(adapterSpecificDataClass, adapterSpecificDataDocument);

            return new IsolateDataMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("isolateDataId"),
                    document.getString("name"),
                    adapterId,
                    adapterSpecificData
            );
        } catch (Exception e) {
            throw new RuntimeException("Error converting Document to IsolateDataMetadata", e);
        }
    }
}
