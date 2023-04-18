package org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.adapters.isolate_data.IsolateDataAdapterId;
import org.phyloviz.pwp.shared.adapters.isolate_data.IsolateDataAdapterRegistry;
import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.specific_data.IsolateDataAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.DocumentConversionException;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;

@ReadingConverter
@RequiredArgsConstructor
public class IsolateDataMetadataDeserializer implements Converter<Document, IsolateDataMetadata> {
    private final MongoConverter mongoConverter;

    private final IsolateDataAdapterRegistry isolateDataAdapterRegistry;

    @Override
    public IsolateDataMetadata convert(@NotNull Document document) {
        try {
            IsolateDataAdapterId adapterId = IsolateDataAdapterId.valueOf(
                    document.getString("adapterId").toUpperCase()
            );

            Class<? extends IsolateDataAdapterSpecificData> adapterSpecificDataClass =
                    isolateDataAdapterRegistry.getIsolateDataAdapterSpecificDataClass(adapterId);

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
            throw new DocumentConversionException("Error converting Document to IsolateDataMetadata:" + e);
        }
    }
}
