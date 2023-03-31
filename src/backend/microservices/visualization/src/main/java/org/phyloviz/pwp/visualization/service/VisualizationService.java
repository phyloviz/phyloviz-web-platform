package org.phyloviz.pwp.visualization.service;

import org.phyloviz.pwp.visualization.service.dtos.getTreeView.GetTreeViewOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getTypingDataDetails.GetTypingDataDetailsOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getTypingDataProfiles.GetTypingDataProfilesOutputDTO;
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
    GetTypingDataDetailsOutputDTO getDatasetDetails(String id);

    /**
     * Gets the profiles of a typing data, given its id.
     *
     * @param id     the id of the typing data
     * @param limit  the number of profiles to be returned
     * @param offset the offset of the profiles to be returned
     * @return the profiles of the typing data
     */
    GetTypingDataProfilesOutputDTO getTypingDataProfiles(String id, int limit, int offset);

    /**
     * Gets the tree view, given its id.
     *
     * @param id the id of the tree view
     * @return the tree view
     */
    GetTreeViewOutputDTO getTreeView(String id);
}
