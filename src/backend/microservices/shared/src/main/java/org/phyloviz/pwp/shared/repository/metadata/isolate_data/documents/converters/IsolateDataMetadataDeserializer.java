package org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.DistanceMatrixDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.specific_data.DistanceMatrixDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.isolate_data.IsolateDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.registry.isolate_data.IsolateDataDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.metadata.DocumentConversionException;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@ReadingConverter
@RequiredArgsConstructor
public class IsolateDataMetadataDeserializer implements Converter<Document, IsolateDataMetadata> {
    private final MongoConverter mongoConverter;

    private final IsolateDataDataRepositoryRegistry isolateDataDataRepositoryRegistry;

    @Override
    public IsolateDataMetadata convert(@NotNull Document document) {
        try {
            Map<String, Document> repositorySpecificDataMap1 =
                    document.get("repositorySpecificData", Map.class);

            Map<IsolateDataDataRepositoryId, IsolateDataDataRepositorySpecificData> repositorySpecificDataMap =
                    new HashMap<>();

            repositorySpecificDataMap1.forEach((repositoryIdString, repositorySpecificDataDocument) -> {
                IsolateDataDataRepositoryId repositoryId = IsolateDataDataRepositoryId.valueOf(
                        repositoryIdString.toUpperCase()
                );

                Class<? extends IsolateDataDataRepositorySpecificData> repositorySpecificDataClass =
                        isolateDataDataRepositoryRegistry.getRepositorySpecificDataClass(repositoryId);

                repositorySpecificDataMap.put(
                        repositoryId,
                        mongoConverter.read(repositorySpecificDataClass, repositorySpecificDataDocument)
                );
            });

            return new IsolateDataMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("isolateDataId"),
                    document.getList("keys", String.class),
                    document.getString("name"),
                    repositorySpecificDataMap
            );
        } catch (Exception e) {
            throw new DocumentConversionException("Error converting Document to IsolateDataMetadata:" + e);
        }
    }
}
