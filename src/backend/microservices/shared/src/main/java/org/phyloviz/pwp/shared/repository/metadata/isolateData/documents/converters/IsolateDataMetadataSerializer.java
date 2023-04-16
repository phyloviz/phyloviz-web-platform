package org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.IsolateDataMetadata;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Objects;

@WritingConverter
@RequiredArgsConstructor
public class IsolateDataMetadataSerializer implements Converter<IsolateDataMetadata, Document> {
    private final MongoConverter mongoConverter;

    @Override
    public Document convert(@NotNull IsolateDataMetadata isolateDataMetadata) {
        try {
            return new Document(
                    Map.of(
                            "_id", isolateDataMetadata.getId(),
                            "projectId", isolateDataMetadata.getProjectId(),
                            "isolateDataId", isolateDataMetadata.getIsolateDataId(),
                            "name", isolateDataMetadata.getName(),
                            "adapterId", isolateDataMetadata.getAdapterId().name().toLowerCase(),
                            "adapterSpecificData", Objects.requireNonNull(
                                    mongoConverter.convertToMongoType(isolateDataMetadata.getAdapterSpecificData())
                            )
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException("Error converting IsolateDataMetadata to Document", e);
        }
    }
}
