package org.phyloviz.pwp.visualization.http.models.typing_data;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.TypingDataProfile;

import java.util.List;

/**
 * Model for a profile.
 */
@Data
public class TypingDataProfileModel {
    private String id;
    private List<String> profile;

    public TypingDataProfileModel(TypingDataProfile typingDataProfile) {
        this.id = typingDataProfile.getId();
        this.profile = typingDataProfile.getProfile().stream().toList();
    }
}
