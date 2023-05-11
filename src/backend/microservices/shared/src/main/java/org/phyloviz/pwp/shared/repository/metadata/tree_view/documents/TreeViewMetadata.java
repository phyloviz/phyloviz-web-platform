package org.phyloviz.pwp.shared.repository.metadata.tree_view.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.tree_view.TreeViewDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.source.TreeViewSource;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tree-views")
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
    /*private Filters filters = { "nodeSizeByIsolateNumber": true,
            "showNodeLabels": true,
            "showLinkWeightLabels": true,
            "typingDataColumns": [],
            "isolateDataColumns": ["country"];*/
    private TreeViewSource source;
    private TreeViewDataRepositoryId repositoryId;
    private TreeViewDataRepositorySpecificData repositorySpecificData;

    public TreeViewMetadata(String projectId, String datasetId, String treeViewId, String name, String layout, TreeViewSource source,
                            TreeViewDataRepositoryId repositoryId, TreeViewDataRepositorySpecificData repositorySpecificData) {
        this.projectId = projectId;
        this.datasetId = datasetId;
        this.treeViewId = treeViewId;
        this.name = name;
        this.layout = layout;
        this.source = source;
        this.repositoryId = repositoryId;
        this.repositorySpecificData = repositorySpecificData;
    }
}
