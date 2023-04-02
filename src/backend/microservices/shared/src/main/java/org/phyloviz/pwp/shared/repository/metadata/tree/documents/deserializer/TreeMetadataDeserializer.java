package org.phyloviz.pwp.shared.repository.metadata.tree.documents.deserializer;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeS3AdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSource;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceAlgorithmDistanceMatrix;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceAlgorithmTypingData;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceFile;
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
            String sourceType = document.getString("sourceType");
            String adapterId = document.getString("adapterId");

            Class<? extends TreeSource> sourceClass;
            Class<? extends TreeAdapterSpecificData> adapterSpecificDataClass;

            sourceClass = switch (sourceType) { // TODO change this (Factory implementation?)
                case "algorithmDistanceMatrix" -> TreeSourceAlgorithmDistanceMatrix.class;
                case "algorithmTypingData" -> TreeSourceAlgorithmTypingData.class;
                case "file" -> TreeSourceFile.class;
                default -> throw new RuntimeException("Unknown sourceType: " + sourceType);
            };

            if (adapterId.equals("s3")) { // TODO change this (Factory implementation?)
                adapterSpecificDataClass = TreeS3AdapterSpecificData.class;
            } else {
                throw new RuntimeException("Unknown adapterId: " + adapterId);
            }

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
            throw new RuntimeException("Error converting Document to TreeAdapterSpecificData", e);
        }
    }
}
