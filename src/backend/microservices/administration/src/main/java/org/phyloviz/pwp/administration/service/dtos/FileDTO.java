package org.phyloviz.pwp.administration.service.dtos;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.documents.File;

@Data
public class FileDTO {
    private final String id;
    private final String originalFileName;

    public FileDTO(File file) {
        this.id = file.getId();
        this.originalFileName = file.getOriginalFileName();
    }
}
