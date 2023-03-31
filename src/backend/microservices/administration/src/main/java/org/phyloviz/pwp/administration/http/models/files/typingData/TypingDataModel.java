package org.phyloviz.pwp.administration.http.models.files.typingData;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.files.TypingDataDTO;

@Data
public class TypingDataModel {
    private String typingDataId;
    private String name;

    public TypingDataModel(TypingDataDTO typingDataDTO) {
        this.typingDataId = typingDataDTO.getTypingDataId();
        this.name = typingDataDTO.getName();
    }
}
