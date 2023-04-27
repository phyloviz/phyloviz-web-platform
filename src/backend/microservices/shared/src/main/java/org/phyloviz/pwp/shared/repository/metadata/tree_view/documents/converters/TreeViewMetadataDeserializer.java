package org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.data.registry.tree_view.TreeViewDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.data.tree_view.TreeViewDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.metadata.DocumentConversionException;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.source.TreeViewSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;

@ReadingConverter
@RequiredArgsConstructor
public class TreeViewMetadataDeserializer implements Converter<Document, TreeViewMetadata> {
    private final MongoConverter mongoConverter;

    private final TreeViewDataRepositoryRegistry treeViewDataRepositoryRegistry;

    @Override
    public TreeViewMetadata convert(@NotNull Document document) {
        try {
            TreeViewDataRepositoryId repositoryId = TreeViewDataRepositoryId.valueOf(
                    document.getString("repositoryId").toUpperCase()
            );

            Class<? extends TreeViewDataRepositorySpecificData> repositorySpecificDataClass =
                    treeViewDataRepositoryRegistry.getRepositorySpecificDataClass(repositoryId);

            Document repositorySpecificDataDocument = (Document) document.get("repositorySpecificData");
            TreeViewDataRepositorySpecificData repositorySpecificData = mongoConverter.read(repositorySpecificDataClass, repositorySpecificDataDocument);

            return new TreeViewMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("datasetId"),
                    document.getString("treeViewId"),
                    document.getString("name"),
                    document.getString("layout"),
                    mongoConverter.read(TreeViewSource.class, (Document) document.get("source")),
                    repositoryId,
                    repositorySpecificData
            );
        } catch (Exception e) {
            throw new DocumentConversionException("Error converting Document to TreeViewMetadata:" + e);
        }
    }
}
