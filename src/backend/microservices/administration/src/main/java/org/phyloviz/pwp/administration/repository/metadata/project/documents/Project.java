package org.phyloviz.pwp.administration.repository.metadata.project.documents;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * A project is a collection of resources.
 */
@Data
@NoArgsConstructor
@Document(collection = "projects")
public class Project {
    @Id
    private String id;

    private String name;
    private String description;
    private String ownerId;
    private List<Resource> resources;

    public Project(String name, String description, String ownerId, List<Resource> resources) {
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
        this.resources = resources;
    }

    public Project(String id, String name, String description, String ownerId, List<Resource> resources) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
        this.resources = resources;
    }
}
