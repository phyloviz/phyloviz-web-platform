package org.phyloviz.pwp.shared.repository.metadata.treeView.documents.deserializer;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.source.TreeViewSource;
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
            TreeViewAdapterId adapterId = TreeViewAdapterId.valueOf(
                    document.getString("adapterId").toUpperCase()
            );

            Class<? extends TreeViewAdapterSpecificData> adapterSpecificDataClass = adapterId.getAdapterSpecificDataClass();

            Document adapterSpecificDataDocument = (Document) document.get("adapterSpecificData");
            TreeViewAdapterSpecificData adapterSpecificData = mongoConverter.read(adapterSpecificDataClass, adapterSpecificDataDocument);

            return new TreeViewMetadata(
                    document.getObjectId("_id").toString(),
                    document.getString("projectId"),
                    document.getString("datasetId"),
                    document.getString("treeViewId"),
                    document.getString("name"),
                    document.getString("layout"),
                    document.get("source", TreeViewSource.class),
                    adapterId,
                    adapterSpecificData
            );
        } catch (Exception e) {
            throw new RuntimeException("Error converting Document to TreeViewMetadata", e);
        }
    }
}
