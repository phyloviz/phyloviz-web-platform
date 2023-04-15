package org.phyloviz.pwp.administration.http.models.datasets.getDatasets;

import lombok.Data;
import org.phyloviz.pwp.administration.http.models.datasets.DatasetModel;
import org.phyloviz.pwp.shared.service.dtos.dataset.DatasetDTO;

import java.util.List;

@Data
public class GetDatasetsOutputModel {
    private List<DatasetModel> datasets;

    public GetDatasetsOutputModel(List<DatasetDTO> datasetsDTO) {
        this.datasets = datasetsDTO.stream().map(DatasetModel::new).toList();
    }
}
