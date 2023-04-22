package org.phyloviz.pwp.shared.repository.metadata.dataset;

import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;

import java.util.List;
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

    /**
     * Find all datasets by project id.
     *
     * @param projectId the id of the project
     * @return a list of datasets
     */
    List<Dataset> findAllByProjectId(String projectId);

    Optional<Dataset> findByProjectIdAndId(String projectId, String datasetId);

    boolean existsByProjectIdAndId(String projectId, String datasetId);

    boolean existsByProjectIdAndTypingDataId(String projectId, String typingDataId);

    boolean existsByProjectIdAndIsolateDataId(String projectId, String isolateDataId);
}
