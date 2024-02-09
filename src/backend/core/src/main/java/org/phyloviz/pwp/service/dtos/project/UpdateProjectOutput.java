package org.phyloviz.pwp.service.dtos.project;

import lombok.Data;

@Data
public class UpdateProjectOutput {
    private final String previousName;
    private final String newName;
    private final String previousDescription;
    private final String newDescription;
}
