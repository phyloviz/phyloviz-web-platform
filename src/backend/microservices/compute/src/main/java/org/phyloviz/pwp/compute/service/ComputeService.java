package org.phyloviz.pwp.compute.service;

import org.phyloviz.pwp.compute.service.dtos.computeDistanceMatrix.ComputeDistanceMatrixOutputDTO;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;
import org.springframework.stereotype.Service;

/**
 * Service for the Compute Microservice.
 */
@Service
public interface ComputeService {

    ComputeDistanceMatrixOutputDTO computeDistanceMatrix(String projectId, String typingDataId, UserDTO toDTO);
}
