package org.phyloviz.pwp.administration.http.controllers.projects.datasets;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.http.models.datasets.create_dataset.CreateDatasetInputModel;
import org.phyloviz.pwp.administration.http.models.datasets.create_dataset.CreateDatasetOutputModel;
import org.phyloviz.pwp.administration.http.models.datasets.delete_dataset.DeleteDatasetOutputModel;
import org.phyloviz.pwp.administration.http.models.datasets.get_dataset.GetDatasetOutputModel;
import org.phyloviz.pwp.administration.http.models.datasets.get_datasets.GetDatasetsOutputModel;
import org.phyloviz.pwp.administration.http.models.datasets.set_isolate_data_of_dataset.SetIsolateDataOfDatasetInputModel;
import org.phyloviz.pwp.administration.http.models.datasets.set_isolate_data_of_dataset.SetIsolateDataOfDatasetOutputModel;
import org.phyloviz.pwp.administration.http.models.datasets.update_dataset.UpdateDatasetInputModel;
import org.phyloviz.pwp.administration.http.models.datasets.update_dataset.UpdateDatasetOutputModel;
import org.phyloviz.pwp.administration.service.dtos.dataset.CreateDatasetOutput;
import org.phyloviz.pwp.administration.service.dtos.dataset.FullDatasetInfo;
import org.phyloviz.pwp.administration.service.dtos.dataset.SetIsolateDataOfDatasetOutput;
import org.phyloviz.pwp.administration.service.dtos.dataset.UpdateDatasetOutput;
import org.phyloviz.pwp.administration.service.project.dataset.DatasetService;
import org.phyloviz.pwp.shared.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controller that handles requests related to datasets.
 */
@RestController
@RequiredArgsConstructor
public class DatasetsController {

    private final DatasetService datasetService;

    /**
     * Creates a dataset.
     *
     * @param projectId               the id of the project to which the dataset will belong
     * @param createDatasetInputModel the dataset information
     * @param user                    the user that is creating the dataset
     * @return the created dataset
     */
    @PostMapping("/projects/{projectId}/datasets")
    public ResponseEntity<CreateDatasetOutputModel> createDataset(
            @PathVariable String projectId,
            @RequestBody CreateDatasetInputModel createDatasetInputModel,
            User user
    ) {
        CreateDatasetOutput createDatasetOutput = datasetService.createDataset(
                createDatasetInputModel.getName(),
                createDatasetInputModel.getDescription(),
                createDatasetInputModel.getTypingDataId(),
                createDatasetInputModel.getIsolateDataId(),
                createDatasetInputModel.getIsolateDataKey(),
                projectId,
                user.getId()
        );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{datasetId}")
                .buildAndExpand(createDatasetOutput.getDatasetId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(new CreateDatasetOutputModel(createDatasetOutput));
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
        FullDatasetInfo fullDatasetInfo = datasetService.getFullDatasetInfo(projectId, datasetId, user.getId());

        return new GetDatasetOutputModel(fullDatasetInfo);
    }

    /**
     * Deletes a dataset.
     *
     * @param projectId the id of the project to which the dataset belongs
     * @param datasetId the id of the dataset to be deleted
     * @param user      the user that is deleting the dataset
     * @return the deleted dataset
     */
    @DeleteMapping("/projects/{projectId}/datasets/{datasetId}")
    public DeleteDatasetOutputModel deleteDataset(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            User user
    ) {
        datasetService.deleteDataset(projectId, datasetId, user.getId());

        return new DeleteDatasetOutputModel(projectId, datasetId);
    }

    /**
     * Updates a dataset.
     *
     * @param projectId the id of the project to which the dataset belongs
     * @param datasetId the id of the dataset to be updated
     * @param user      the user that is updating the dataset
     * @return information about the update
     */
    @PatchMapping("/projects/{projectId}/datasets/{datasetId}")
    public UpdateDatasetOutputModel updateDataset(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            @RequestBody UpdateDatasetInputModel updateDatasetInputModel,
            User user
    ) {
        UpdateDatasetOutput updateDatasetOutput = datasetService.updateDataset(
                updateDatasetInputModel.getName(), updateDatasetInputModel.getDescription(),
                projectId, datasetId, user.getId()
        );

        return new UpdateDatasetOutputModel(updateDatasetOutput);
    }

    /**
     * Sets the isolate data of a dataset.
     *
     * @param projectId the id of the project to which the dataset belongs
     * @param datasetId the id of the dataset to be updated
     * @param user      the user that is updating the dataset
     * @return information about the update
     */
    @PostMapping("/projects/{projectId}/datasets/{datasetId}/isolateData")
    public SetIsolateDataOfDatasetOutputModel setIsolateDataOfDataset(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            @RequestBody SetIsolateDataOfDatasetInputModel setIsolateDataOfDatasetInputModel,
            User user
    ) {
        SetIsolateDataOfDatasetOutput setIsolateDataOfDatasetOutput = datasetService.setIsolateDataOfDataset(
                setIsolateDataOfDatasetInputModel.getIsolateDataId(), setIsolateDataOfDatasetInputModel.getIsolateDataKey(),
                projectId, datasetId, user.getId()
        );

        return new SetIsolateDataOfDatasetOutputModel(setIsolateDataOfDatasetOutput);
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
        List<FullDatasetInfo> fullDatasetInfos = datasetService.getFullDatasetInfos(projectId, user.getId());

        return new GetDatasetsOutputModel(fullDatasetInfos);
    }
}
