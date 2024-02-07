package org.phyloviz.pwp.http.models.typing_data.get_typing_data_profiles;

import lombok.Data;
import org.phyloviz.pwp.http.models.typing_data.TypingDataProfileModel;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataProfilesOutput;

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
