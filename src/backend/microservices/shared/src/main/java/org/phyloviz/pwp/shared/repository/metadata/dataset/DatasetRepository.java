package org.phyloviz.pwp.shared.repository.metadata.dataset;

import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;

import java.util.Optional;

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
    Optional<Dataset> findById(String resourceId);
}
