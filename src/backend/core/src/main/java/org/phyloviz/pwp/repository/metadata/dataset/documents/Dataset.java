package org.phyloviz.pwp.repository.metadata.dataset.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "datasets")
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
    private String isolateDataKey;

    public Dataset(String projectId, String name, String description, String typingDataId, String isolateDataId,
                   String isolateDataKey) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.typingDataId = typingDataId;
        this.isolateDataId = isolateDataId;
        this.isolateDataKey = isolateDataKey;
    }
}
