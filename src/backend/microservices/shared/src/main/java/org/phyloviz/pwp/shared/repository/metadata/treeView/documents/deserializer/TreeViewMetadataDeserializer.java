package org.phyloviz.pwp.shared.repository.metadata.treeView.documents.deserializer;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewS3AdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;

@ReadingConverter
@RequiredArgsConstructor
public class TreeViewMetadataDeserializer implements Converter<Document, TreeViewMetadata> {
    private final MongoConverter mongoConverter;

    @Override
    public TreeViewMetadata convert(@NotNull Document document) {
        try {
            String adapterId = document.getString("adapterId");

            Class<? extends TreeViewAdapterSpecificData> adapterSpecificDataClass;

            if (adapterId.equals("s3")) { // TODO change this (Factory implementation?)
                adapterSpecificDataClass = TreeViewS3AdapterSpecificData.class;
            } else {
                throw new RuntimeException("Unknown adapterId: " + adapterId);
            }

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
            throw new RuntimeException("Error converting Document to TreeViewAdapterSpecificData", e);
        }
    }
}
