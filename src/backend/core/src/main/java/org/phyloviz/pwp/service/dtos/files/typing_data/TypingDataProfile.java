package org.phyloviz.pwp.service.dtos.files.typing_data;

import lombok.Data;

import java.util.List;

/**
 * Profile of a typing data.
 */
@Data
public class TypingDataProfile {
    private final String id;
    private final List<String> profile;
}
