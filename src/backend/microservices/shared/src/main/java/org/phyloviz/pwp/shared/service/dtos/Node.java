package org.phyloviz.pwp.shared.service.dtos;

import lombok.Data;

import java.util.List;

/**
 * DTO for a tree node.
 */
@Data
public class Node {
    private final String st;
    private final double[] coordinates;
    private final List<String> profile;
}
