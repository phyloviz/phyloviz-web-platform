package org.phyloviz.pwp.administration.service.dtos.tree_view;

import lombok.Data;

@Data
public class UpdateTreeViewOutput {
    private final String previousName;
    private final String newName;
}
