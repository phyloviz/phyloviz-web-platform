package org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.DistanceMatrixDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.specific_data.DistanceMatrixDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.registry.distance_matrix.DistanceMatrixDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.metadata.DocumentConversionException;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.source.DistanceMatrixSource;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.source.DistanceMatrixSourceType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@ReadingConverter
@RequiredArgsConstructor
public class DistanceMatrixMetadataDeserializer implements Converter<Document, DistanceMatrixMetadata> {
    private final MongoConverter mongoConverter;

    private final DistanceMatrixDataRepositoryRegistry distanceMatrixDataRepositoryRegistry;

    @Override
    public DistanceMatrixMetadata convert(@NotNull Document document) {
        try {
            DistanceMatrixSourceType sourceType = DistanceMatrixSourceType.valueOf(
                    document.getString("sourceType").toUpperCase()
            );
            Class<? extends DistanceMatrixSource> sourceClass = sourceType.getSourceClass();
            Document sourceDocument = (Document) document.get("source");
            DistanceMatrixSource source = mongoConverter.read(sourceClass, sourceDocument);

            Map<String, Document> repositorySpecificDataMap1 =
                    document.get("repositorySpecificData", Map.class);

            Map<DistanceMatrixDataRepositoryId, DistanceMatrixDataRepositorySpecificData> repositorySpecificDataMap =
                    new HashMap<>();

            repositorySpecificDataMap1.forEach((repositoryIdString, repositorySpecificDataDocument) -> {
                DistanceMatrixDataRepositoryId repositoryId = DistanceMatrixDataRepositoryId.valueOf(
                        repositoryIdString.toUpperCase()
                );

                Class<? extends DistanceMatrixDataRepositorySpecificData> repositorySpecificDataClass =
                        distanceMatrixDataRepositoryRegistry.getRepositorySpecificDataClass(repositoryId);

                repositorySpecificDataMap.put(
                        repositoryId,
                        mongoConverter.read(repositorySpecificDataClass, repositorySpecificDataDocument)
                );
            });

            return new DistanceMatrixMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("datasetId"),
                    document.getString("distanceMatrixId"),
                    document.getString("name"),
                    sourceType,
                    source,
                    repositorySpecificDataMap
            );
        } catch (Exception e) {
            throw new DocumentConversionException("Error converting Document to DistanceMatrixMetadata:" + e);
        }
    }
}
