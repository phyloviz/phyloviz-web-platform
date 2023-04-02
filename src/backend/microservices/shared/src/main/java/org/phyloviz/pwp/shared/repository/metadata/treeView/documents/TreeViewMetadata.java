package org.phyloviz.pwp.shared.repository.metadata.treeView.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tree-views")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("treeViewMetadata")
public class TreeViewMetadata {
    @Id
    private String id;

    private String projectId;
    private String treeViewId;
    private String name;
    private String layout;
    private TreeViewSource source;
    private String url;
    private String adapterId;
    private TreeViewAdapterSpecificData adapterSpecificData;

    public TreeViewMetadata(String projectId, String treeViewId, String name, String layout, TreeViewSource source,
                            String url, String adapterId, TreeViewAdapterSpecificData adapterSpecificData) {
        this.projectId = projectId;
        this.treeViewId = treeViewId;
        this.name = name;
        this.layout = layout;
        this.source = source;
        this.url = url;
        this.adapterId = adapterId;
        this.adapterSpecificData = adapterSpecificData;
    }
}
