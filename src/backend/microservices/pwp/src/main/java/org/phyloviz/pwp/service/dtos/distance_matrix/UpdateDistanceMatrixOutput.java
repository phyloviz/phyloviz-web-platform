package org.phyloviz.pwp.service.dtos.distance_matrix;

import lombok.Data;

@Data
public class UpdateDistanceMatrixOutput {
    private final String previousName;
    private final String newName;
}
