package org.phyloviz.pwp.administration.http.models.distanceMatrices.deleteDistanceMatrix;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteDistanceMatrixOutputModel {
    private String projectId;
    private String datasetId;
    private String distanceMatrixId;
}
