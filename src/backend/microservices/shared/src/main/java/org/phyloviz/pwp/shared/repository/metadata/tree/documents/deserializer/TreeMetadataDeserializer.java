package org.phyloviz.pwp.shared.repository.metadata.tree.documents.deserializer;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSource;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;

@ReadingConverter
@RequiredArgsConstructor
public class TreeMetadataDeserializer implements Converter<Document, TreeMetadata> {
    private final MongoConverter mongoConverter;

    @Override
    public TreeMetadata convert(@NotNull Document document) {
        try {
            TreeSourceType sourceType = TreeSourceType.valueOf(
                    document.getString("sourceType").toUpperCase()
            );

            TreeAdapterId adapterId = TreeAdapterId.valueOf(
                    document.getString("adapterId").toUpperCase()
            );

            Class<? extends TreeSource> sourceClass = sourceType.getSourceClass();
            Class<? extends TreeAdapterSpecificData> adapterSpecificDataClass = adapterId.getAdapterSpecificDataClass();

            Document sourceDocument = (Document) document.get("source");
            TreeSource source = mongoConverter.read(sourceClass, sourceDocument);

            Document adapterSpecificDataDocument = (Document) document.get("adapterSpecificData");
            TreeAdapterSpecificData adapterSpecificData = mongoConverter.read(adapterSpecificDataClass, adapterSpecificDataDocument);

            return new TreeMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("datasetId"),
                    document.getString("treeId"),
                    document.getString("name"),
                    sourceType,
                    source,
                    adapterId,
                    adapterSpecificData
            );
        } catch (Exception e) {
            throw new RuntimeException("Error converting Document to TreeAdapterMetadata", e);
        }
    }
}
