package org.phyloviz.pwp.administration.http.models.files.typing_data;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.files.typing_data.TypingDataInfo;

@Data
public class TypingDataModel {
    private String typingDataId;
    private String name;

    public TypingDataModel(TypingDataInfo typingDataInfo) {
        this.typingDataId = typingDataInfo.getTypingDataId();
        this.name = typingDataInfo.getName();
    }
}
