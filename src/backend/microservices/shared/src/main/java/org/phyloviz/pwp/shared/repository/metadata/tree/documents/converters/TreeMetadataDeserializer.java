package org.phyloviz.pwp.shared.repository.metadata.tree.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.data.registry.tree.TreeDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.data.tree.TreeDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree.repository.specific_data.TreeDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.metadata.DocumentConversionException;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
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

    private final TreeDataRepositoryRegistry treeDataRepositoryRegistry;

    @Override
    public TreeMetadata convert(@NotNull Document document) {
        try {
            TreeSourceType sourceType = TreeSourceType.valueOf(
                    document.getString("sourceType").toUpperCase()
            );

            TreeDataRepositoryId repositoryId = TreeDataRepositoryId.valueOf(
                    document.getString("repositoryId").toUpperCase()
            );

            Class<? extends TreeSource> sourceClass = sourceType.getSourceClass();
            Class<? extends TreeDataRepositorySpecificData> repositorySpecificDataClass =
                    treeDataRepositoryRegistry.getRepositorySpecificDataClass(repositoryId);

            Document sourceDocument = (Document) document.get("source");
            TreeSource source = mongoConverter.read(sourceClass, sourceDocument);

            Document repositorySpecificDataDocument = (Document) document.get("repositorySpecificData");
            TreeDataRepositorySpecificData repositorySpecificData = mongoConverter.read(repositorySpecificDataClass, repositorySpecificDataDocument);

            return new TreeMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("datasetId"),
                    document.getString("treeId"),
                    document.getString("name"),
                    sourceType,
                    source,
                    repositoryId,
                    repositorySpecificData
            );
        } catch (Exception e) {
            throw new DocumentConversionException("Error converting Document to TreeMetadata:" + e);
        }
    }
}
