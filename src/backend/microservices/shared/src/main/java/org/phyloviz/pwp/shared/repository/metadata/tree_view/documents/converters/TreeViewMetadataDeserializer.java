package org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.adapters.tree_view.TreeViewAdapterId;
import org.phyloviz.pwp.shared.adapters.tree_view.TreeViewAdapterRegistry;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.specific_data.TreeViewAdapterSpecificData;
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

    private final TreeViewAdapterRegistry treeViewAdapterRegistry;

    @Override
    public TreeViewMetadata convert(@NotNull Document document) {
        try {
            TreeViewAdapterId adapterId = TreeViewAdapterId.valueOf(
                    document.getString("adapterId").toUpperCase()
            );

            Class<? extends TreeViewAdapterSpecificData> adapterSpecificDataClass =
                    treeViewAdapterRegistry.getTreeViewAdapterSpecificDataClass(adapterId);

            Document adapterSpecificDataDocument = (Document) document.get("adapterSpecificData");
            TreeViewAdapterSpecificData adapterSpecificData = mongoConverter.read(adapterSpecificDataClass, adapterSpecificDataDocument);

            return new TreeViewMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("datasetId"),
                    document.getString("treeViewId"),
                    document.getString("name"),
                    document.getString("layout"),
                    mongoConverter.read(TreeViewSource.class, (Document) document.get("source")),
                    adapterId,
                    adapterSpecificData
            );
        } catch (Exception e) {
            throw new DocumentConversionException("Error converting Document to TreeViewMetadata:" + e);
        }
    }
}
