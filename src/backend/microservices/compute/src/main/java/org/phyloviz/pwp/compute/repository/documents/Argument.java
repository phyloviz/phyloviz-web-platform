package org.phyloviz.pwp.compute.repository.documents;

import lombok.Data;

@Data
public class Argument {
    private final String id;
    //    private final String description; TODO: Maybe add this later?
    private final String defaultValue;
}
