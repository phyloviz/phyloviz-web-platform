package org.phyloviz.pwp.shared.repository.metadata.project.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * A project is a collection of resources.
 */
@Document(collection = "#{@constants.projectsCollection}")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    private String id;

    private String name;
    private String description;
    private String ownerId;
    private List<String> datasetIds;
    private FileIds fileIds;

    public Project(String name, String description, String ownerId, List<String> datasetIds, FileIds fileIds) {
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
        this.datasetIds = datasetIds;
        this.fileIds = fileIds;
    }
}
