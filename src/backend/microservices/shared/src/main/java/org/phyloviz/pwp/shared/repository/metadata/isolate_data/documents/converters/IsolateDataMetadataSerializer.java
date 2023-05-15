package org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.metadata.DocumentConversionException;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@WritingConverter
@RequiredArgsConstructor
public class IsolateDataMetadataSerializer implements Converter<IsolateDataMetadata, Document> {
    private final MongoConverter mongoConverter;

    @Override
    public Document convert(@NotNull IsolateDataMetadata isolateDataMetadata) {
        try {
            return new Document(
                    Map.of(
                            "projectId", isolateDataMetadata.getProjectId(),
                            "isolateDataId", isolateDataMetadata.getIsolateDataId(),
                            "keys", isolateDataMetadata.getKeys(),
                            "name", isolateDataMetadata.getName(),
                            "repositorySpecificData", Objects.requireNonNull(
                                    mongoConverter.convertToMongoType(
                                            isolateDataMetadata.getRepositorySpecificData().entrySet().stream()
                                                    .collect(Collectors.toMap(
                                                            entry -> entry.getKey().name().toLowerCase(),
                                                            Map.Entry::getValue
                                                    ))
                                    )
                            )
                    )
            );
        } catch (Exception e) {
            throw new DocumentConversionException("Error converting IsolateDataMetadata to Document:" + e);
        }
    }
}
