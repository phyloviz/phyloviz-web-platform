package org.phyloviz.pwp.administration.http.controllers.models;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.FileDTO;

@Data
public class FileModel {
    private String id;
    private String type;

    public FileModel(FileDTO fileDTO) {
        this.id = fileDTO.getId();
        this.type = fileDTO.getOriginalFileName();
    }
}
