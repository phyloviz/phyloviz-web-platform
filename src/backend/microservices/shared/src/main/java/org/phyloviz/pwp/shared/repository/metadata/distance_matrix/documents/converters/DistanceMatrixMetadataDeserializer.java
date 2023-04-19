package org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAdapterId;
import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAdapterRegistry;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.specific_data.DistanceMatrixAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.DocumentConversionException;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.source.DistanceMatrixSource;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.source.DistanceMatrixSourceType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;

@ReadingConverter
@RequiredArgsConstructor
public class DistanceMatrixMetadataDeserializer implements Converter<Document, DistanceMatrixMetadata> {
    private final MongoConverter mongoConverter;

    private final DistanceMatrixAdapterRegistry distanceMatrixAdapterRegistry;

    @Override
    public DistanceMatrixMetadata convert(@NotNull Document document) {
        try {
            DistanceMatrixSourceType sourceType = DistanceMatrixSourceType.valueOf(
                    document.getString("sourceType").toUpperCase()
            );
            DistanceMatrixAdapterId adapterId = DistanceMatrixAdapterId.valueOf(
                    document.getString("adapterId")
            );

            Class<? extends DistanceMatrixSource> sourceClass = sourceType.getSourceClass();
            Class<? extends DistanceMatrixAdapterSpecificData> adapterSpecificDataClass =
                    distanceMatrixAdapterRegistry.getDistanceMatrixAdapterSpecificDataClass(adapterId);

            Document sourceDocument = (Document) document.get("source");
            DistanceMatrixSource source = mongoConverter.read(sourceClass, sourceDocument);

            Document adapterSpecificDataDocument = (Document) document.get("adapterSpecificData");
            DistanceMatrixAdapterSpecificData adapterSpecificData = mongoConverter.read(adapterSpecificDataClass, adapterSpecificDataDocument);

            return new DistanceMatrixMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("datasetId"),
                    document.getString("distanceMatrixId"),
                    document.getString("name"),
                    sourceType,
                    source,
                    adapterId,
                    adapterSpecificData
            );
        } catch (Exception e) {
            throw new DocumentConversionException("Error converting Document to DistanceMatrixMetadata:" + e);
        }
    }
}
