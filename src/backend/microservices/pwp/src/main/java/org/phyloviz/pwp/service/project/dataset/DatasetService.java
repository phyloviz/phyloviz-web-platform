package org.phyloviz.pwp.service.project.dataset;

import org.phyloviz.pwp.service.dtos.dataset.CreateDatasetOutput;
import org.phyloviz.pwp.service.dtos.dataset.FullDatasetInfo;
import org.phyloviz.pwp.service.dtos.dataset.SetIsolateDataOfDatasetOutput;
import org.phyloviz.pwp.service.dtos.dataset.UpdateDatasetOutput;

import java.util.List;

/**
 * Service for managing datasets.
 */
public interface DatasetService {

    /**
     * Creates a new dataset.
     *
     * @param name           the name of the dataset
     * @param description    the description of the dataset
     * @param typingDataId   the id of the typing data
     * @param isolateDataId  the id of the isolate data
     * @param isolateDataKey the key of the isolate data
     * @param projectId      the id of the project
     * @param userId         the id of the user
     * @return the output of the dataset creation
     */
    CreateDatasetOutput createDataset(String name, String description, String typingDataId, String isolateDataId,
                                      String isolateDataKey, String projectId, String userId);

    /**
     * Gets the full information of a dataset.
     *
     * @param projectId the id of the project
     * @param datasetId the id of the dataset
     * @param userId    the id of the user that is requesting the dataset information
     * @return the full information of the dataset
     */
    FullDatasetInfo getFullDatasetInfo(String projectId, String datasetId, String userId);

    /**
     * Gets the datasets of a project.
     *
     * @param projectId the id of the project
     * @param userId    the id of the user that is requesting the datasets information
     * @return the datasets of the project
     */
    List<FullDatasetInfo> getFullDatasetInfos(String projectId, String userId);

    /**
     * Gets the datasets of a project.
     *
     * @param projectId the id of the project
     * @return the datasets of the project
     */
    List<FullDatasetInfo> getFullDatasetInfos(String projectId);

    /**
     * Deletes a dataset.
     *
     * @param projectId the id of the project
     * @param datasetId the id of the dataset
     * @param userId    the id of the user that is deleting the dataset
     */
    void deleteDataset(String projectId, String datasetId, String userId);

    /**
     * Deletes all the datasets of a project.
     *
     * @param projectId the id of the project
     */
    void deleteAllByProjectId(String projectId);

    /**
     * Updates a dataset.
     *
     * @param name        the name of the dataset
     * @param description the description of the dataset
     * @param projectId   the id of the project
     * @param datasetId   the id of the dataset
     * @param userId      the id of the user that is updating the dataset
     * @return the output of the dataset update
     */
    UpdateDatasetOutput updateDataset(String name, String description, String projectId, String datasetId, String userId);

    /**
     * Sets the isolate data of a dataset.
     *
     * @param isolateDataId  the id of the isolate data
     * @param isolateDataKey the key of the isolate data
     * @param projectId      the id of the project
     * @param datasetId      the id of the dataset
     * @param userId         the id of the user that is setting the isolate data
     * @return the output of the isolate data setting
     */
    SetIsolateDataOfDatasetOutput setIsolateDataOfDataset(String isolateDataId, String isolateDataKey, String projectId, String datasetId, String userId);
}
