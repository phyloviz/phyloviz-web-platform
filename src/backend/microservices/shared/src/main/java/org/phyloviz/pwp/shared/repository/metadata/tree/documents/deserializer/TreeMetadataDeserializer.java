package org.phyloviz.pwp.shared.repository.metadata.tree.documents.deserializer;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterSpecificDataFactory;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeS3AdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;

@ReadingConverter
@RequiredArgsConstructor
public class TreeMetadataDeserializer implements Converter<Document, TreeMetadata> {
    private final MongoConverter mongoConverter;

    private final TreeSourceFactory treeSourceFactory;
    private final TreeAdapterSpecificDataFactory treeAdapterSpecificDataFactory;

    @Override
    public TreeMetadata convert(@NotNull Document document) {
        try {
            String sourceType = document.getString("sourceType");
            String adapterId = document.getString("adapterId");

            Class<? extends TreeSource> sourceClass = treeSourceFactory.getClass(sourceType);
            Class<? extends TreeAdapterSpecificData> adapterSpecificDataClass =
                    treeAdapterSpecificDataFactory.getClass(adapterId);

            Document sourceDocument = (Document) document.get("source");
            TreeSource source = mongoConverter.read(sourceClass, sourceDocument);

            Document adapterSpecificDataDocument = (Document) document.get("adapterSpecificData");
            TreeAdapterSpecificData adapterSpecificData = mongoConverter.read(adapterSpecificDataClass, adapterSpecificDataDocument);

            return new TreeMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("treeId"),
                    document.getString("name"),
                    document.getString("sourceType"),
                    source,
                    document.getString("url"),
                    document.getString("adapterId"),
                    adapterSpecificData
            );
        } catch (Exception e) {
            throw new RuntimeException("Error converting Document to TreeAdapterMetadata", e);
        }
    }
}
