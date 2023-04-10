package org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataSchema;

import lombok.Data;

@Data
public class GetTypingDataSchemaInputDTO {
    private final String projectId;
    private final String typingDataId;
}
