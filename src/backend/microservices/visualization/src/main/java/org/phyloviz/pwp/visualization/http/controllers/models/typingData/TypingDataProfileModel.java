package org.phyloviz.pwp.visualization.http.controllers.models.typingData;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.TypingDataProfileDTO;

/**
 * Model for a profile.
 */
@Data
public class TypingDataProfileModel {
    private int id;
    private String[] profile;

    public TypingDataProfileModel(TypingDataProfileDTO typingDataProfileDTO) {
        this.id = typingDataProfileDTO.getId();
        this.profile = typingDataProfileDTO.getProfile();
    }
}
