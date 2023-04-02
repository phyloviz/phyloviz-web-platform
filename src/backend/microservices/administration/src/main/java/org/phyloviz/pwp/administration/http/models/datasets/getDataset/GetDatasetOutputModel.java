package org.phyloviz.pwp.administration.http.models.datasets.getDataset;

import org.phyloviz.pwp.administration.http.models.datasets.DatasetModel;
import org.phyloviz.pwp.administration.service.dtos.datasets.DatasetDTO;

public class GetDatasetOutputModel extends DatasetModel {
    public GetDatasetOutputModel(DatasetDTO datasetDTO) {
        super(datasetDTO);
    }
}
