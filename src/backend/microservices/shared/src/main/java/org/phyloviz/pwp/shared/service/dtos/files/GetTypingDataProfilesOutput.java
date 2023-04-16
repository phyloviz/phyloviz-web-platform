package org.phyloviz.pwp.shared.service.dtos.files;

import lombok.Data;

import java.util.List;

/**
 * Output DTO for the getTypingDataProfiles service.
 */
@Data
public class GetTypingDataProfilesOutput {
    private final List<TypingDataProfileDTO> profiles;
    private final int totalCount;
}
