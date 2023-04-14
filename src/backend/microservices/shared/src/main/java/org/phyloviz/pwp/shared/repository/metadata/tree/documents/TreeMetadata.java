package org.phyloviz.pwp.shared.repository.metadata.tree.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSource;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("treeMetadata")
public class TreeMetadata {
    @Id
    private String id;

    private String projectId;
    private String datasetId;
    private String treeId;
    private String name;
    private String sourceType;
    private TreeSource source;
    private String url;
    private String adapterId;
    private TreeAdapterSpecificData adapterSpecificData;

    public TreeMetadata(String projectId, String datasetId, String treeId, String name, String sourceType, TreeSource source, String url,
                        String adapterId, TreeAdapterSpecificData adapterSpecificData) {
        this.projectId = projectId;
        this.datasetId = datasetId;
        this.treeId = treeId;
        this.name = name;
        this.sourceType = sourceType;
        this.source = source;
        this.url = url;
        this.adapterId = adapterId;
        this.adapterSpecificData = adapterSpecificData;
    }
}
