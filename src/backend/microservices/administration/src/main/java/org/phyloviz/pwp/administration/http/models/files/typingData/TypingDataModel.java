package org.phyloviz.pwp.administration.http.models.files.typingData;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.TypingDataMetadataDTO;

@Data
public class TypingDataModel {
    private String typingDataId;
    private String name;

    public TypingDataModel(TypingDataMetadataDTO typingDataMetadataDTO) {
        this.typingDataId = typingDataMetadataDTO.getTypingDataId();
        this.name = typingDataMetadataDTO.getName();
    }
}
