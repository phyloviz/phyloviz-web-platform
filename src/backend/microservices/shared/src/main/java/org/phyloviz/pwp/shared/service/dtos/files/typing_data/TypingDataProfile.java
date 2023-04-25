package org.phyloviz.pwp.shared.service.dtos.files.typing_data;

import lombok.Data;

/**
 * Profile of a typing data.
 */
@Data
public class TypingDataProfile {
    private final int id;
    private final String[] profile;
}
