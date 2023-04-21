package org.phyloviz.pwp.visualization.http.controllers.models.typing_data;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.TypingDataProfile;

/**
 * Model for a profile.
 */
@Data
public class TypingDataProfileModel {
    private int id;
    private String[] profile;

    public TypingDataProfileModel(TypingDataProfile typingDataProfile) {
        this.id = typingDataProfile.getId();
        this.profile = typingDataProfile.getProfile();
    }
}
