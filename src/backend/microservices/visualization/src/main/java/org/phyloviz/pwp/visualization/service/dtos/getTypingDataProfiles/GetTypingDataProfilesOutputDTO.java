package org.phyloviz.pwp.visualization.service.dtos.getTypingDataProfiles;

import org.phyloviz.pwp.visualization.service.dtos.shared.ProfileDTO;

/**
 * Output DTO for the getTypingDataProfiles service.
 */
public class GetTypingDataProfilesOutputDTO {
    private final ProfileDTO[] profiles;
    private final int totalCount;

    public GetTypingDataProfilesOutputDTO(ProfileDTO[] profiles, int totalCount) {
        this.profiles = profiles;
        this.totalCount = totalCount;
    }

    public ProfileDTO[] getProfiles() {
        return profiles;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
