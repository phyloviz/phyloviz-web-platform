package org.phyloviz.pwp.visualization.service.dtos.getTypingDatasetProfiles;

import org.phyloviz.pwp.visualization.service.dtos.shared.ProfileDTO;

/**
 * Output DTO for the getDatasetProfiles service.
 */
public class GetTypingDatasetProfilesOutputDTO {
    private final ProfileDTO[] profiles;
    private final int totalCount;

    public GetTypingDatasetProfilesOutputDTO(ProfileDTO[] profiles, int totalCount) {
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
