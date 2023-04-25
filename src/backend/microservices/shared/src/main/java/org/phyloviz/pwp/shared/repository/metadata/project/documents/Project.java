package org.phyloviz.pwp.shared.repository.metadata.project.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A project is a collection of resources.
 */
@Document(collection = "#{@mongoMetadataCollectionNames.projectsCollection}")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    private String id;

    private String name;
    private String description;
    private String ownerId;

    public Project(String name, String description, String ownerId) {
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
    }
}
