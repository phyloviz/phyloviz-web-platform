package org.phyloviz.pwp.http.models.distance_matrices.update_distance_matrix;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.distance_matrix.UpdateDistanceMatrixOutput;

@Data
public class UpdateDistanceMatrixOutputModel {
    private String name;

    public UpdateDistanceMatrixOutputModel(UpdateDistanceMatrixOutput updateDistanceMatrixOutput) {
        if (updateDistanceMatrixOutput.getNewName() != null) {
            this.name = String.format("Changed from '%s' to '%s'", updateDistanceMatrixOutput.getPreviousName(), updateDistanceMatrixOutput.getNewName());
        } else {
            this.name = "No changes";
        }
    }
}
