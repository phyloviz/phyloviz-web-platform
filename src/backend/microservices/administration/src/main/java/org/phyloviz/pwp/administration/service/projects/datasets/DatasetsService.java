package org.phyloviz.pwp.administration.service.projects.datasets;

import org.phyloviz.pwp.administration.service.dtos.datasets.DatasetDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.createDataset.CreateDatasetInputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.createDataset.CreateDatasetOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.deleteDataset.DeleteDatasetInputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.deleteDataset.DeleteDatasetOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.getDataset.GetDatasetInputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.getDatasets.GetDatasetsInputDTO;

import java.util.List;

/**
 * Service for operations related to datasets.
 */
public interface DatasetsService {

    /**
     * Creates a dataset.
     *
     * @param createDatasetInputDTO the input data for the dataset creation
     * @return the output data from the dataset creation
     */
    CreateDatasetOutputDTO createDataset(CreateDatasetInputDTO createDatasetInputDTO);

    /**
     * Gets a dataset.
     *
     * @param getDatasetInputDTO the input data for the dataset retrieval
     * @return the dataset
     */
    DatasetDTO getDataset(GetDatasetInputDTO getDatasetInputDTO);

    /**
     * Deletes a dataset.
     *
     * @param deleteDatasetInputDTO the input data for the dataset deletion
     * @return the output data for the dataset deletion
     */
    DeleteDatasetOutputDTO deleteDataset(DeleteDatasetInputDTO deleteDatasetInputDTO);

    /**
     * Gets all datasets belonging to a certain project.
     *
     * @param getDatasetsInputDTO the input data for the datasets retrieval
     * @return the datasets
     */
    List<DatasetDTO> getDatasets(GetDatasetsInputDTO getDatasetsInputDTO);

    /**
     * Gets a dataset.
     * This method is also used by other services (ProjectsService) to allow for the recursive retrieval of resources.
     *
     * @param datasetId id of the dataset
     */
    DatasetDTO getDataset(String datasetId);

    /**
     * Deletes a dataset.
     * This method is also used by other services (ProjectsService) to allow for the recursive deletion of resources.
     * Does not delete its own id from the project.
     *
     * @param datasetId the id of the dataset
     */
    void deleteDataset(String datasetId);
}
