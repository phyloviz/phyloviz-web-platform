package org.phyloviz.pwp.administration.http.models.datasets.get_datasets;

import lombok.Data;
import org.phyloviz.pwp.administration.http.models.datasets.DatasetModel;
import org.phyloviz.pwp.administration.service.dtos.dataset.FullDatasetInfo;

import java.util.List;

@Data
public class GetDatasetsOutputModel {
    private List<DatasetModel> datasets;

    public GetDatasetsOutputModel(List<FullDatasetInfo> fullDatasetInfos) {
        this.datasets = fullDatasetInfos.stream().map(DatasetModel::new).toList();
    }
}
