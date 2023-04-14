package org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.deserializer;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.*;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData.DistanceMatrixAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData.DistanceMatrixAdapterSpecificDataFactory;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData.DistanceMatrixS3AdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.source.DistanceMatrixSource;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.source.DistanceMatrixSourceFactory;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.source.DistanceMatrixSourceFunction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@ReadingConverter
@RequiredArgsConstructor
public class DistanceMatrixMetadataDeserializer implements Converter<Document, DistanceMatrixMetadata> {
    private final MongoConverter mongoConverter;

    private final DistanceMatrixSourceFactory distanceMatrixSourceFactory;
    private final DistanceMatrixAdapterSpecificDataFactory distanceMatrixAdapterSpecificDataFactory;

    @Override
    public DistanceMatrixMetadata convert(@NotNull Document document) {
        try {
            String sourceType = document.getString("sourceType");
            String adapterId = document.getString("adapterId");

            Class<? extends DistanceMatrixSource> sourceClass = distanceMatrixSourceFactory.getClass(sourceType);
            Class<? extends DistanceMatrixAdapterSpecificData> adapterSpecificDataClass =
                    distanceMatrixAdapterSpecificDataFactory.getClass(adapterId);

            Document sourceDocument = (Document) document.get("source");
            DistanceMatrixSource source = mongoConverter.read(sourceClass, sourceDocument);

            Document adapterSpecificDataDocument = (Document) document.get("adapterSpecificData");
            DistanceMatrixAdapterSpecificData adapterSpecificData = mongoConverter.read(adapterSpecificDataClass, adapterSpecificDataDocument);

            return new DistanceMatrixMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("distanceMatrixId"),
                    document.getString("name"),
                    document.getString("sourceType"),
                    source,
                    document.getString("url"),
                    document.getString("adapterId"),
                    adapterSpecificData
            );
        } catch (Exception e) {
            throw new RuntimeException("Error converting Document to DistanceMatrixMetadata", e);
        }
    }
}
