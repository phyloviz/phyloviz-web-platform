package org.phyloviz.pwp.shared.repository.metadata.tree.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeMetadata {
    @Id
    private String id;

    private String projectId;
    private String treeId;
    private String name;
    private String sourceType;
    private TreeSource source;
    private String url;
    private String adapterId;
    private TreeAdapterSpecificData adapterSpecificData;

    public TreeMetadata(String projectId, String treeId, String name, String sourceType, TreeSource source, String url,
                        String adapterId, TreeAdapterSpecificData adapterSpecificData) {
        this.projectId = projectId;
        this.treeId = treeId;
        this.name = name;
        this.sourceType = sourceType;
        this.source = source;
        this.url = url;
        this.adapterId = adapterId;
        this.adapterSpecificData = adapterSpecificData;
    }
}
