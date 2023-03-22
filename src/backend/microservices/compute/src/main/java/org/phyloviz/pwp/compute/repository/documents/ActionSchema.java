package org.phyloviz.pwp.compute.repository.documents;

import java.util.List;
import lombok.Data;

@Data
public class ActionSchema {
    private final String command;
    private final List<Argument> arguments;
}
