package visualization.service;

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
    void getDataset(String id);

    /**
     * Gets the profiles of a dataset, given its id.
     *
     * @param id     the id of the dataset
     * @param limit  the number of profiles to be returned
     * @param offset the offset of the profiles to be returned
     * @return the profiles of the dataset
     */
    void getDatasetProfiles(String id, int limit, int offset);

    /**
     * Gets the tree view, given its id.
     *
     * @param id the id of the tree view
     * @return the tree view
     */
    void getTreeView(String id);
}
