package org.phyloviz.pwp.visualization.service.dtos.getDatasetProfiles;

import org.phyloviz.pwp.visualization.service.dtos.shared.ProfileDTO;

/**
 * Output DTO for the getDatasetProfiles service.
 */
public class GetDatasetProfilesOutputDTO {
    private final ProfileDTO[] profiles;
    private final int totalCount;

    public GetDatasetProfilesOutputDTO(ProfileDTO[] profiles, int totalCount) {
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
