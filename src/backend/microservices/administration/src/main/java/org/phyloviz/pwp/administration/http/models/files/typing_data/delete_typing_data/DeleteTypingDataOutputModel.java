package org.phyloviz.pwp.administration.http.models.files.typing_data.delete_typing_data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteTypingDataOutputModel {
    private String projectId;
    private String typingDataId;
}
