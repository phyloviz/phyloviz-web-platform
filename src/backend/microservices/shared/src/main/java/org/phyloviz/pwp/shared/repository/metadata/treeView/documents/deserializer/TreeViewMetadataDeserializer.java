package org.phyloviz.pwp.shared.repository.metadata.treeView.documents.deserializer;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewAdapterSpecificDataFactory;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewS3AdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.source.TreeViewSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;

@ReadingConverter
@RequiredArgsConstructor
public class TreeViewMetadataDeserializer implements Converter<Document, TreeViewMetadata> {
    private final MongoConverter mongoConverter;

    private final TreeViewAdapterSpecificDataFactory treeViewAdapterSpecificDataFactory;

    @Override
    public TreeViewMetadata convert(@NotNull Document document) {
        try {
            String adapterId = document.getString("adapterId");

            Class<? extends TreeViewAdapterSpecificData> adapterSpecificDataClass =
                    treeViewAdapterSpecificDataFactory.getClass(adapterId);

            Document adapterSpecificDataDocument = (Document) document.get("adapterSpecificData");
            TreeViewAdapterSpecificData adapterSpecificData = mongoConverter.read(adapterSpecificDataClass, adapterSpecificDataDocument);

            return new TreeViewMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("treeViewId"),
                    document.getString("name"),
                    document.getString("layout"),
                    document.get("source", TreeViewSource.class),
                    document.getString("url"),
                    document.getString("adapterId"),
                    adapterSpecificData
            );
        } catch (Exception e) {
            throw new RuntimeException("Error converting Document to TreeViewMetadata", e);
        }
    }
}
