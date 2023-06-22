package org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.data.registry.tree_view.TreeViewDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.data.tree_view.TreeViewDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.metadata.DocumentConversionException;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.source.TreeViewSource;
import org.phyloviz.pwp.shared.service.dtos.tree_view.Transformations;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@ReadingConverter
@RequiredArgsConstructor
public class TreeViewMetadataDeserializer implements Converter<Document, TreeViewMetadata> {
    private final MongoConverter mongoConverter;

    private final TreeViewDataRepositoryRegistry treeViewDataRepositoryRegistry;

    @Override
    public TreeViewMetadata convert(@NotNull Document document) {
        try {
            Map<String, Document> repositorySpecificDataMap1 =
                    document.get("repositorySpecificData", Map.class);

            Map<TreeViewDataRepositoryId, TreeViewDataRepositorySpecificData> repositorySpecificDataMap =
                    new HashMap<>();

            repositorySpecificDataMap1.forEach((repositoryIdString, repositorySpecificDataDocument) -> {
                TreeViewDataRepositoryId repositoryId = TreeViewDataRepositoryId.valueOf(
                        repositoryIdString.toUpperCase()
                );

                Class<? extends TreeViewDataRepositorySpecificData> repositorySpecificDataClass =
                        treeViewDataRepositoryRegistry.getRepositorySpecificDataClass(repositoryId);

                repositorySpecificDataMap.put(
                        repositoryId,
                        mongoConverter.read(repositorySpecificDataClass, repositorySpecificDataDocument)
                );
            });

            Document transformationsDocument = (Document) document.get("transformations");

            return new TreeViewMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("datasetId"),
                    document.getString("treeViewId"),
                    document.getString("name"),
                    document.getString("layout"),
                    mongoConverter.read(TreeViewSource.class, (Document) document.get("source")),
                    repositorySpecificDataMap,
                    transformationsDocument != null ? mongoConverter.read(Transformations.class, transformationsDocument) : null
            );
        } catch (Exception e) {
            throw new DocumentConversionException("Error converting Document to TreeViewMetadata:" + e);
        }
    }
}
