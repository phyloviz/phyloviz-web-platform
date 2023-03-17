package org.phyloviz.pwp.administration.service.dtos;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.documents.Resource;

@Data
public class ResourceDTO {
    private final String id;
    private final String collection;

    public ResourceDTO(Resource resource) {
        this.id = resource.getId();
        this.collection = resource.getCollection();
    }
}
