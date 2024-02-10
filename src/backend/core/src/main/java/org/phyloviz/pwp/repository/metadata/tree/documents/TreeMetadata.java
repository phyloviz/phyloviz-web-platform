package org.phyloviz.pwp.repository.metadata.tree.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.phyloviz.pwp.repository.data.tree.TreeDataRepositoryId;
import org.phyloviz.pwp.repository.data.tree.repository.specific_data.TreeDataRepositorySpecificData;
import org.phyloviz.pwp.repository.metadata.tree.documents.source.TreeSource;
import org.phyloviz.pwp.repository.metadata.tree.documents.source.TreeSourceType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "trees")
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
    private Map<TreeDataRepositoryId, TreeDataRepositorySpecificData> repositorySpecificData;

    public TreeMetadata(String projectId, String datasetId, String treeId, String name, TreeSourceType sourceType, TreeSource source,
                        Map<TreeDataRepositoryId, TreeDataRepositorySpecificData> repositorySpecificData) {
        this.projectId = projectId;
        this.datasetId = datasetId;
        this.treeId = treeId;
        this.name = name;
        this.sourceType = sourceType;
        this.source = source;
        this.repositorySpecificData = repositorySpecificData;
    }
}
