package org.phyloviz.pwp.visualization.http.controllers.models.typingData.getTypingDataProfiles;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.GetTypingDataProfilesOutput;
import org.phyloviz.pwp.visualization.http.controllers.models.typingData.TypingDataProfileModel;

import java.util.List;

/**
 * Output model for the get typing data profiles endpoint.
 */
@Data
public class GetTypingDataProfilesOutputModel {
    private List<TypingDataProfileModel> profiles;
    private int totalCount;

    public GetTypingDataProfilesOutputModel(GetTypingDataProfilesOutput getTypingDataProfilesOutput) {
        this.profiles = getTypingDataProfilesOutput.getProfiles().stream().map(TypingDataProfileModel::new).toList();
        this.totalCount = getTypingDataProfilesOutput.getTotalCount();
    }
}
