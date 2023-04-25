package org.phyloviz.pwp.administration.service.dtos.distance_matrix;

import lombok.Data;

@Data
public class UpdateDistanceMatrixOutput {
    private final String previousName;
    private final String newName;
}
