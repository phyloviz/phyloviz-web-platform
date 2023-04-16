package org.phyloviz.pwp.shared.service.dtos.files;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataMetadata;

@Data
public class TypingDataMetadataDTO {
    private final String typingDataId;
    private final String name;

    public TypingDataMetadataDTO(TypingDataMetadata typingDataMetadata) {
        this.typingDataId = typingDataMetadata.getTypingDataId();
        this.name = typingDataMetadata.getName();
    }
}