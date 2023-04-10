package org.phyloviz.pwp.visualization.http.controllers.models.typingData.getTypingDataProfiles;

import lombok.Data;
import org.phyloviz.pwp.visualization.http.controllers.models.typingData.TypingDataProfileModel;
import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataProfiles.GetTypingDataProfilesOutputDTO;

import java.util.List;

/**
 * Output model for the get typing data profiles endpoint.
 */
@Data
public class GetTypingDataProfilesOutputModel {
    private List<TypingDataProfileModel> profiles;
    private int totalCount;

    public GetTypingDataProfilesOutputModel(GetTypingDataProfilesOutputDTO getTypingDataProfilesOutputDTO) {
        this.profiles = getTypingDataProfilesOutputDTO.getProfiles().stream().map(TypingDataProfileModel::new).toList();
        this.totalCount = getTypingDataProfilesOutputDTO.getTotalCount();
    }
}
