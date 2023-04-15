package org.phyloviz.pwp.administration.http.models.files.typingData.deleteTypingData;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteTypingDataOutputModel {
    private String projectId;
    private String typingDataId;
}
