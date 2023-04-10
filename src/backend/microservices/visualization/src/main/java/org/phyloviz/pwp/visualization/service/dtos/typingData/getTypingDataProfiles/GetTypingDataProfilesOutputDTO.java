package org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataProfiles;

import lombok.Data;
import org.phyloviz.pwp.visualization.service.dtos.typingData.TypingDataProfileDTO;

import java.util.List;

/**
 * Output DTO for the getTypingDataProfiles service.
 */
@Data
public class GetTypingDataProfilesOutputDTO {
    private final List<TypingDataProfileDTO> profiles;
    private final int totalCount;
}
