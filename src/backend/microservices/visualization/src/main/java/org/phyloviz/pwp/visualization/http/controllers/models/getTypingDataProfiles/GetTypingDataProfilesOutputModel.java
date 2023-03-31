package org.phyloviz.pwp.visualization.http.controllers.models.getTypingDataProfiles;

import org.phyloviz.pwp.visualization.http.controllers.models.shared.ProfileModel;
import org.phyloviz.pwp.visualization.service.dtos.getTypingDataProfiles.GetTypingDataProfilesOutputDTO;

import java.util.Arrays;

/**
 * Output model for the get typing data profiles endpoint.
 */
public class GetTypingDataProfilesOutputModel {
    private final ProfileModel[] profiles;
    private final int totalCount;

    public GetTypingDataProfilesOutputModel(ProfileModel[] profiles, int totalCount) {
        this.profiles = profiles;
        this.totalCount = totalCount;
    }

    public GetTypingDataProfilesOutputModel(GetTypingDataProfilesOutputDTO getTypingDataProfilesOutputDTO) {
        this.profiles = Arrays.stream(getTypingDataProfilesOutputDTO.getProfiles()).map(ProfileModel::new)
                .toArray(ProfileModel[]::new);
        this.totalCount = getTypingDataProfilesOutputDTO.getTotalCount();
    }
}
