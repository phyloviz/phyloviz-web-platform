package org.phyloviz.pwp.repository.metadata.tree.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.repository.data.registry.tree.TreeDataRepositoryRegistry;
import org.phyloviz.pwp.repository.data.tree.TreeDataRepositoryId;
import org.phyloviz.pwp.repository.data.tree.repository.specific_data.TreeDataRepositorySpecificData;
import org.phyloviz.pwp.repository.metadata.DocumentConversionException;
import org.phyloviz.pwp.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.repository.metadata.tree.documents.source.TreeSource;
import org.phyloviz.pwp.repository.metadata.tree.documents.source.TreeSourceType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@ReadingConverter
@RequiredArgsConstructor
public class TreeMetadataDeserializer implements Converter<Document, TreeMetadata> {
    private final MongoConverter mongoConverter;

    private final TreeDataRepositoryRegistry treeDataRepositoryRegistry;

    @Override
    public TreeMetadata convert(@NotNull Document document) {
        try {
            TreeSourceType sourceType = TreeSourceType.valueOf(
                    document.getString("sourceType").toUpperCase()
            );
            Class<? extends TreeSource> sourceClass = sourceType.getSourceClass();
            Document sourceDocument = (Document) document.get("source");
            TreeSource source = mongoConverter.read(sourceClass, sourceDocument);

            Map<String, Document> repositorySpecificDataMap1 =
                    document.get("repositorySpecificData", Map.class);

            Map<TreeDataRepositoryId, TreeDataRepositorySpecificData> repositorySpecificDataMap =
                    new HashMap<>();

            repositorySpecificDataMap1.forEach((repositoryIdString, repositorySpecificDataDocument) -> {
                TreeDataRepositoryId repositoryId = TreeDataRepositoryId.valueOf(
                        repositoryIdString.toUpperCase()
                );

                Class<? extends TreeDataRepositorySpecificData> repositorySpecificDataClass =
                        treeDataRepositoryRegistry.getRepositorySpecificDataClass(repositoryId);

                repositorySpecificDataMap.put(
                        repositoryId,
                        mongoConverter.read(repositorySpecificDataClass, repositorySpecificDataDocument)
                );
            });

            return new TreeMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("datasetId"),
                    document.getString("treeId"),
                    document.getString("name"),
                    sourceType,
                    source,
                    repositorySpecificDataMap
            );
        } catch (Exception e) {
            throw new DocumentConversionException("Error converting Document to TreeMetadata:" + e);
        }
    }
}
