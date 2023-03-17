package org.phyloviz.pwp.administration.http.controllers.models;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.ResourceDTO;

@Data
public class ResourceModel {
    private String id;
    private String collection;

    public ResourceModel(ResourceDTO resourceDTO) {
        this.id = resourceDTO.getId();
        this.collection = resourceDTO.getCollection();
    }
}
