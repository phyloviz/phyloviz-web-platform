package org.phyloviz.pwp.service.dtos.files.typing_data;

import lombok.Data;

import java.util.List;

/**
 * Output for the profiles of the typing data.
 */
@Data
public class GetTypingDataProfilesOutput {
    private final List<TypingDataProfile> profiles;
    private final int totalCount;
}
