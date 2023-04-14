package org.phyloviz.pwp.visualization.service.projects.datasets.distanceMatrices;

import org.phyloviz.pwp.visualization.service.dtos.getDistanceMatrix.GetDistanceMatrixInputDTO;

public interface DistanceMatrixVisualizationService {

    /**
     * Gets a distance matrix, given its id.
     *
     * @param getDistanceMatrixInputDTO the input DTO
     * @return the distance matrix
     */
    String getDistanceMatrix(GetDistanceMatrixInputDTO getDistanceMatrixInputDTO);
}
