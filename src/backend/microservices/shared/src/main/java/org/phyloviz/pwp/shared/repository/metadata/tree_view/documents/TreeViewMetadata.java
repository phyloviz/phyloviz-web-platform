package org.phyloviz.pwp.shared.repository.metadata.tree_view.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.tree_view.TreeViewDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.source.TreeViewSource;
import org.phyloviz.pwp.shared.service.dtos.tree_view.Transformations;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

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
    private TreeViewSource source;
    private Map<TreeViewDataRepositoryId, TreeViewDataRepositorySpecificData> repositorySpecificData;
    private Transformations transformations;

    public TreeViewMetadata(String projectId, String datasetId, String treeViewId, String name, String layout, TreeViewSource source,
                            Map<TreeViewDataRepositoryId, TreeViewDataRepositorySpecificData> repositorySpecificData, Transformations transformations) {
        this.projectId = projectId;
        this.datasetId = datasetId;
        this.treeViewId = treeViewId;
        this.name = name;
        this.layout = layout;
        this.source = source;
        this.repositorySpecificData = repositorySpecificData;
        this.transformations = transformations;
    }
}
