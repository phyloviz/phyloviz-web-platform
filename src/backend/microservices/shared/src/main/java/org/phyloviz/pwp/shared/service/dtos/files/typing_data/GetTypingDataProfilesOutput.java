package org.phyloviz.pwp.shared.service.dtos.files.typing_data;

import lombok.Data;

import java.util.List;

/**
 * Output DTO for the getTypingDataProfiles service.
 */
@Data
public class GetTypingDataProfilesOutput {
    private final List<TypingDataProfile> profiles;
    private final int totalCount;
}
