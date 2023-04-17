package org.phyloviz.pwp.shared.repository.metadata.dataset.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "#{@constants.datasetsCollection}")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dataset {
    @Id
    private String id;

    private String projectId;
    private String name;
    private String description;
    private String typingDataId;
    private String isolateDataId;
    private List<String> distanceMatrixIds;
    private List<String> treeIds;
    private List<String> treeViewIds;

    public Dataset(String projectId, String name, String description, String typingDataId, String isolateDataId,
                   List<String> distanceMatrixIds, List<String> treeIds, List<String> treeViewIds) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.typingDataId = typingDataId;
        this.isolateDataId = isolateDataId;
        this.distanceMatrixIds = distanceMatrixIds;
        this.treeIds = treeIds;
        this.treeViewIds = treeViewIds;
    }
}
