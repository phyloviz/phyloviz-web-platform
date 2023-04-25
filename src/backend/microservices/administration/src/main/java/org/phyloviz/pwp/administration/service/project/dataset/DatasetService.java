package org.phyloviz.pwp.administration.service.project.dataset;

import org.phyloviz.pwp.administration.service.dtos.dataset.CreateDatasetOutput;
import org.phyloviz.pwp.administration.service.dtos.dataset.FullDatasetInfo;
import org.phyloviz.pwp.administration.service.dtos.dataset.UpdateDatasetOutput;

import java.util.List;

public interface DatasetService {

    CreateDatasetOutput createDataset(String name, String description, String typingDataId, String isolateDataId,
                                      String isolateDataKey, String projectId, String userId);

    FullDatasetInfo getFullDatasetInfo(String projectId, String datasetId, String userId);

    List<FullDatasetInfo> getFullDatasetInfos(String projectId, String userId);

    List<FullDatasetInfo> getFullDatasetInfos(String projectId);

    void deleteDataset(String projectId, String datasetId, String userId);

    void deleteAllByProjectId(String projectId);

    UpdateDatasetOutput updateDataset(String name, String description, String projectId, String datasetId, String userId);
}
