package org.phyloviz.pwp.shared.repository.metadata.documents;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "projects")
public class Project {
    @Id
    private String id;

    private String name;
    private String description;
    private String owner;
    private List<Resource> resources;

    public Project(String name, String description, String owner, List<Resource> resources) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.resources = resources;
    }
}
