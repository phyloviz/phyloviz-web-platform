package org.phyloviz.pwp.administration.service.dtos.files.typing_data;

import lombok.Data;

@Data
public class UpdateTypingDataOutput {
    private final String previousName;
    private final String newName;
}
