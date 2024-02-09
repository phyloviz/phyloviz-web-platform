package org.phyloviz.pwp.http.models.distance_matrices.delete_distance_matrix;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteDistanceMatrixOutputModel {
    private String projectId;
    private String datasetId;
    private String distanceMatrixId;
}
