package org.phyloviz.pwp.service.dtos.files.typing_data;

import lombok.Data;

@Data
public class UpdateTypingDataOutput {
    private final String previousName;
    private final String newName;
}
