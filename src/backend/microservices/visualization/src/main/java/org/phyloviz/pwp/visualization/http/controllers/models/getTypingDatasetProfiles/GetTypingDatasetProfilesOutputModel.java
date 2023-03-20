package org.phyloviz.pwp.visualization.http.controllers.models.getTypingDatasetProfiles;

import java.util.Arrays;
import org.phyloviz.pwp.visualization.http.controllers.models.shared.ProfileModel;
import org.phyloviz.pwp.visualization.service.dtos.getTypingDatasetProfiles.GetTypingDatasetProfilesOutputDTO;

/**
 * Output model for the get typing dataset profiles endpoint.
 */
public class GetTypingDatasetProfilesOutputModel {
    private final ProfileModel[] profiles;
    private final int totalCount;

    public GetTypingDatasetProfilesOutputModel(ProfileModel[] profiles, int totalCount) {
        this.profiles = profiles;
        this.totalCount = totalCount;
    }

    public GetTypingDatasetProfilesOutputModel(GetTypingDatasetProfilesOutputDTO getTypingDatasetProfilesOutputDTO) {
        this.profiles = Arrays.stream(getTypingDatasetProfilesOutputDTO.getProfiles()).map(ProfileModel::new)
                .toArray(ProfileModel[]::new);
        this.totalCount = getTypingDatasetProfilesOutputDTO.getTotalCount();
    }
}
