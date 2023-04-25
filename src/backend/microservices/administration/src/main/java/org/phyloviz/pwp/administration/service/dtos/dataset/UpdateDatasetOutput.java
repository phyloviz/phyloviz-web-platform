package org.phyloviz.pwp.administration.service.dtos.dataset;

import lombok.Data;

@Data
public class UpdateDatasetOutput {
    private final String previousName;
    private final String newName;
    private final String previousDescription;
    private final String newDescription;
}
