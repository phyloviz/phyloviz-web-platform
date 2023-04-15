package org.phyloviz.pwp.shared.service.dtos.files;

import lombok.Data;

/**
 * DTO for a profile.
 */
@Data
public class TypingDataProfileDTO {
    private final int id;
    private final String[] profile;
}
