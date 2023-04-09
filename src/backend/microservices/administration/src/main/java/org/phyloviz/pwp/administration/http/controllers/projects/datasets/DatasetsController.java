package org.phyloviz.pwp.administration.http.controllers.projects.datasets;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.http.models.datasets.createDataset.CreateDatasetInputModel;
import org.phyloviz.pwp.administration.http.models.datasets.createDataset.CreateDatasetOutputModel;
import org.phyloviz.pwp.administration.http.models.datasets.deleteDataset.DeleteDatasetOutputModel;
import org.phyloviz.pwp.administration.http.models.datasets.getDataset.GetDatasetOutputModel;
import org.phyloviz.pwp.administration.service.dtos.datasets.DatasetDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.createDataset.CreateDatasetOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.deleteDataset.DeleteDatasetInputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.deleteDataset.DeleteDatasetOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.getDataset.GetDatasetInputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.getDatasets.GetDatasetsInputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.getDatasets.GetDatasetsOutputModel;
import org.phyloviz.pwp.administration.service.projects.datasets.DatasetsService;
import org.phyloviz.pwp.shared.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DatasetsController {

    private final DatasetsService datasetsService;

    /**
     * Creates a dataset.
     *
     * @param projectId               the id of the project to which the dataset will belong
     * @param createDatasetInputModel the dataset information
     */
    @PostMapping("/projects/{projectId}/datasets")
    public CreateDatasetOutputModel createDataset(
            @PathVariable String projectId,
            @RequestBody CreateDatasetInputModel createDatasetInputModel,
            User user
    ) {
        CreateDatasetOutputDTO createDatasetOutputDTO = datasetsService.createDataset(
                createDatasetInputModel.toDTO(projectId, user)
        );

        return new CreateDatasetOutputModel(createDatasetOutputDTO);
    }

    /**
     * Gets a dataset.
     *
     * @param projectId the id of the project to which the dataset belongs
     * @param datasetId the id of the dataset to be retrieved
     * @param user      the user that is retrieving the dataset
     * @return the dataset
     */
    @GetMapping("/projects/{projectId}/datasets/{datasetId}")
    public GetDatasetOutputModel getDataset(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            User user
    ) {
        DatasetDTO datasetDTO = datasetsService.getDataset(
                new GetDatasetInputDTO(projectId, datasetId, user.toDTO())
        );

        return new GetDatasetOutputModel(datasetDTO);
    }

    /**
     * Deletes a dataset.
     *
     * @param projectId the id of the project to which the dataset belongs
     * @param datasetId the id of the dataset to be deleted
     * @param user      the user that is deleting the dataset
     */
    @DeleteMapping("/projects/{projectId}/datasets/{datasetId}")
    public DeleteDatasetOutputModel deleteDataset(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            User user
    ) {
        DeleteDatasetOutputDTO deleteDatasetOutputDTO = datasetsService.deleteDataset(
                new DeleteDatasetInputDTO(projectId, datasetId, user.toDTO())
        );

        return new DeleteDatasetOutputModel(deleteDatasetOutputDTO);
    }

    /**
     * Gets all datasets belonging to a certain project.
     *
     * @param projectId the id of the project to which the datasets belong
     * @param user      the user that is retrieving the datasets
     * @return the datasets
     */
    @GetMapping("/projects/{projectId}/datasets")
    public GetDatasetsOutputModel getDatasets(
            @PathVariable String projectId,
            User user
    ) {
        List<DatasetDTO> datasetDTOS = datasetsService.getDatasets(
                new GetDatasetsInputDTO(projectId, user.toDTO())
        );

        return new GetDatasetsOutputModel(datasetDTOS);
    }
}
