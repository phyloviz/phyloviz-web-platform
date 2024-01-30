package org.phyloviz.pwp.service.dtos.files.typing_data;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.TypingDataMetadata;

@Data
public class TypingDataInfo {
    private final String typingDataId;
    private final String name;

    public TypingDataInfo(TypingDataMetadata typingDataMetadata) {
        this.typingDataId = typingDataMetadata.getTypingDataId();
        this.name = typingDataMetadata.getName();
    }
}