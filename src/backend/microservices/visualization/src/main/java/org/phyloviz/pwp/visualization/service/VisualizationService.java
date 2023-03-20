package org.phyloviz.pwp.visualization.service;

import org.phyloviz.pwp.visualization.service.dtos.getTypingDatasetDetais.GetDatasetDetailsOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getTypingDatasetProfiles.GetTypingDatasetProfilesOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getTreeView.GetTreeViewOutputDTO;
import org.springframework.stereotype.Service;

/**
 * Service for the Visualization Microservice.
 */
@Service
public interface VisualizationService {

    /**
     * Gets the dataset information, given its id.
     *
     * @param id the id of the dataset
     * @return the dataset information
     */
    GetDatasetDetailsOutputDTO getDatasetDetails(String id);

    /**
     * Gets the profiles of a typing dataset, given its id.
     *
     * @param id     the id of the typing dataset
     * @param limit  the number of profiles to be returned
     * @param offset the offset of the profiles to be returned
     * @return the profiles of the typing dataset
     */
    GetTypingDatasetProfilesOutputDTO getTypingDatasetProfiles(String id, int limit, int offset);

    /**
     * Gets the tree view, given its id.
     *
     * @param id the id of the tree view
     * @return the tree view
     */
    GetTreeViewOutputDTO getTreeView(String id);
}
