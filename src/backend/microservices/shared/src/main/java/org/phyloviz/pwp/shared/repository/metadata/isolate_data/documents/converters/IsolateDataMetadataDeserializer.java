package org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.data.isolate_data.IsolateDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.registry.isolate_data.IsolateDataDataRepositoryRegistry;
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

    private final IsolateDataDataRepositoryRegistry isolateDataDataRepositoryRegistry;

    @Override
    public IsolateDataMetadata convert(@NotNull Document document) {
        try {
            IsolateDataDataRepositoryId repositoryId = IsolateDataDataRepositoryId.valueOf(
                    document.getString("repositoryId").toUpperCase()
            );

            Class<? extends IsolateDataDataRepositorySpecificData> repositorySpecificDataClass =
                    isolateDataDataRepositoryRegistry.getRepositorySpecificDataClass(repositoryId);

            Document repositorySpecificDataDocument = (Document) document.get("repositorySpecificData");
            IsolateDataDataRepositorySpecificData repositorySpecificData = mongoConverter.read(repositorySpecificDataClass, repositorySpecificDataDocument);

            return new IsolateDataMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("isolateDataId"),
                    document.getList("keys", String.class),
                    document.getString("name"),
                    repositoryId,
                    repositorySpecificData
            );
        } catch (Exception e) {
            throw new DocumentConversionException("Error converting Document to IsolateDataMetadata:" + e);
        }
    }
}
