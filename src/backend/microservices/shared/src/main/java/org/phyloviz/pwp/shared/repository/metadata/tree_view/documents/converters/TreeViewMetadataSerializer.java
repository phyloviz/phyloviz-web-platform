package org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.phyloviz.pwp.shared.repository.metadata.DocumentConversionException;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@WritingConverter
@RequiredArgsConstructor
public class TreeViewMetadataSerializer implements Converter<TreeViewMetadata, Document> {
    private final MongoConverter mongoConverter;

    @Override
    public Document convert(@NotNull TreeViewMetadata treeViewMetadata) {
        try {
            return new Document(
                    Map.of(
                            "_id", Objects.requireNonNull(mongoConverter.convertId(treeViewMetadata.getId(), ObjectId.class)),
                            "projectId", treeViewMetadata.getProjectId(),
                            "datasetId", treeViewMetadata.getDatasetId(),
                            "treeViewId", treeViewMetadata.getTreeViewId(),
                            "name", treeViewMetadata.getName(),
                            "layout", treeViewMetadata.getLayout(),
                            "source", Objects.requireNonNull(mongoConverter.convertToMongoType(treeViewMetadata.getSource())),
                            "repositorySpecificData", Objects.requireNonNull(
                                    mongoConverter.convertToMongoType(
                                            treeViewMetadata.getRepositorySpecificData().entrySet().stream()
                                                    .collect(Collectors.toMap(
                                                            entry -> entry.getKey().name().toLowerCase(),
                                                            Map.Entry::getValue
                                                    ))
                                    )
                            ),
                            "transformations", Objects.requireNonNull(mongoConverter.convertToMongoType(treeViewMetadata.getTransformations()))
                    )
            );
        } catch (Exception e) {
            throw new DocumentConversionException("Error converting TreeViewMetadata to Document: " + e);
        }
    }
}