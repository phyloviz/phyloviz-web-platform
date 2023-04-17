package org.phyloviz.pwp.shared.repository.metadata.tree.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSource;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "#{@constants.treeMetadataCollection}")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeMetadata {
    @Id
    private String id;

    private String projectId;
    private String datasetId;
    private String treeId;
    private String name;
    private TreeSourceType sourceType;
    private TreeSource source;
    private TreeAdapterId adapterId;
    private TreeAdapterSpecificData adapterSpecificData;

    public TreeMetadata(String projectId, String datasetId, String treeId, String name, TreeSourceType sourceType, TreeSource source,
                        TreeAdapterId adapterId, TreeAdapterSpecificData adapterSpecificData) {
        this.projectId = projectId;
        this.datasetId = datasetId;
        this.treeId = treeId;
        this.name = name;
        this.sourceType = sourceType;
        this.source = source;
        this.adapterId = adapterId;
        this.adapterSpecificData = adapterSpecificData;
    }
}
