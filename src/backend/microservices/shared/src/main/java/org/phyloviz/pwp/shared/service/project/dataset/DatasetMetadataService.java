package org.phyloviz.pwp.shared.service.project.dataset;

import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;

import java.util.List;

public interface DatasetMetadataService {

    Dataset getDataset(String projectId, String datasetId, String userId);

    Dataset getDataset(String datasetId);

    Dataset getDatasetOrNull(String datasetId);

    List<Dataset> getAllDatasetsByProjectId(String projectId);

    Dataset saveDataset(Dataset dataset);

    void deleteDataset(String datasetId);

    void assertExists(String projectId, String datasetId, String userId);
}
