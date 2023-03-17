package org.phyloviz.pwp.administration.service.dtos;

import lombok.Data;
import org.phyloviz.pwp.administration.repository.project.File;

@Data
public class FileDTO {
    private final String id;
    private final String type;

    public FileDTO(File file) {
        this.id = file.getId();
        this.type = file.getType();
    }
}
