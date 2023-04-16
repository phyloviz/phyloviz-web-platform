package org.phyloviz.pwp.shared.service.project.dataset;

import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.service.dtos.dataset.CreateDatasetOutput;
import org.phyloviz.pwp.shared.service.dtos.dataset.DatasetDTO;

import java.util.List;

public interface DatasetService {

    CreateDatasetOutput createDataset(String name, String description, String typingDataId, String isolateDataId,
                                      String projectId, String userId);

    Dataset getDataset(String projectId, String datasetId, String userId);

    Dataset getDataset(String datasetId);

    Dataset getDatasetOrNull(String datasetId);

    DatasetDTO getDatasetDTO(String projectId, String datasetId, String userId);

    DatasetDTO getDatasetDTO(String datasetId);

    List<Dataset> getDatasets(String projectId, String userId);

    List<DatasetDTO> getDatasetDTOs(String projectId, String userId);

    void assertExists(String projectId, String datasetId, String userId);

    Dataset saveDataset(Dataset dataset);

    void deleteDataset(String projectId, String datasetId, String userId);

    void deleteDataset(String datasetId);
}
