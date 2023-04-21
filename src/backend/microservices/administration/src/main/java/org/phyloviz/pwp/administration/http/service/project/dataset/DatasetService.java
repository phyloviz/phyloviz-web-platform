package org.phyloviz.pwp.administration.http.service.project.dataset;

import org.phyloviz.pwp.shared.service.dtos.dataset.CreateDatasetOutput;
import org.phyloviz.pwp.shared.service.dtos.dataset.FullDatasetInfo;

import java.util.List;

public interface DatasetService {

    CreateDatasetOutput createDataset(String name, String description, String typingDataId, String isolateDataId,
                                      String projectId, String userId);

    FullDatasetInfo getFullDatasetInfo(String projectId, String datasetId, String userId);

    List<FullDatasetInfo> getFullDatasetInfos(String projectId, String userId);

    List<FullDatasetInfo> getFullDatasetInfos(String projectId);

    void deleteDataset(String projectId, String datasetId, String userId);

    void deleteDataset(String datasetId);
}
