package org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataProfiles;

import lombok.Data;

@Data
public class GetTypingDataProfilesInputDTO {
    private final String projectId;
    private final String typingDataId;
    private final int limit;
    private final int offset;
}
