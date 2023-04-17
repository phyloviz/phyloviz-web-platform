package org.phyloviz.pwp.shared.repository.metadata.treeView.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.source.TreeViewSource;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "#{@constants.treeViewMetadataCollection}")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeViewMetadata {
    @Id
    private String id;

    private String projectId;
    private String datasetId;
    private String treeViewId;
    private String name;
    private String layout;
    private TreeViewSource source;
    private TreeViewAdapterId adapterId;
    private TreeViewAdapterSpecificData adapterSpecificData;

    public TreeViewMetadata(String projectId, String datasetId, String treeViewId, String name, String layout, TreeViewSource source,
                            TreeViewAdapterId adapterId, TreeViewAdapterSpecificData adapterSpecificData) {
        this.projectId = projectId;
        this.datasetId = datasetId;
        this.treeViewId = treeViewId;
        this.name = name;
        this.layout = layout;
        this.source = source;
        this.adapterId = adapterId;
        this.adapterSpecificData = adapterSpecificData;
    }
}
