package org.phyloviz.pwp.administration.repository.metadata.dataset;

import org.phyloviz.pwp.administration.repository.metadata.dataset.documents.Dataset;

public interface DatasetRepository {

    /**
     * Saves a dataset.
     *
     * @param dataset the dataset to save
     * @return the saved dataset
     */
    Dataset save(Dataset dataset);

    /**
     * Deletes a dataset.
     *
     * @param dataset the dataset to delete
     */
    void delete(Dataset dataset);

    /**
     * Find a dataset by id.
     *
     * @param resourceId the id of the dataset
     * @return a dataset
     */
    Dataset findById(String resourceId);
}
