package org.phyloviz.pwp.visualization.http.controllers.models.getDatasetProfiles;

import java.util.Arrays;
import org.phyloviz.pwp.visualization.http.controllers.models.shared.ProfileModel;
import org.phyloviz.pwp.visualization.service.dtos.getDatasetProfiles.GetDatasetProfilesOutputDTO;

/**
 * Output model for the get dataset profiles endpoint.
 */
public class GetDatasetProfilesOutputModel {
    private final ProfileModel[] profiles;
    private final int totalCount;

    public GetDatasetProfilesOutputModel(ProfileModel[] profiles, int totalCount) {
        this.profiles = profiles;
        this.totalCount = totalCount;
    }

    public GetDatasetProfilesOutputModel(GetDatasetProfilesOutputDTO getDatasetProfilesOutputDTO) {
        this.profiles = Arrays.stream(getDatasetProfilesOutputDTO.getProfiles()).map(ProfileModel::new)
                .toArray(ProfileModel[]::new);
        this.totalCount = getDatasetProfilesOutputDTO.getTotalCount();
    }
}
