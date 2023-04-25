package org.phyloviz.pwp.administration.service.dtos.files.isolate_data;

import lombok.Data;

@Data
public class UpdateIsolateDataOutput {
    private final String previousName;
    private final String newName;
}
