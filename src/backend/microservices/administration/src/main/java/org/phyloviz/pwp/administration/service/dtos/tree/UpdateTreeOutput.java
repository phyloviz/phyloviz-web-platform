package org.phyloviz.pwp.administration.service.dtos.tree;

import lombok.Data;

@Data
public class UpdateTreeOutput {
    private final String previousName;
    private final String newName;
}
