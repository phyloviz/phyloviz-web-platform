package org.phyloviz.pwp.visualization.http.models.getDatasetProfiles;

import org.phyloviz.pwp.visualization.http.models.shared.ProfileModel;
import org.phyloviz.pwp.visualization.service.dtos.getDatasetProfiles.GetDatasetProfilesOutputDTO;

import java.util.Arrays;

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
