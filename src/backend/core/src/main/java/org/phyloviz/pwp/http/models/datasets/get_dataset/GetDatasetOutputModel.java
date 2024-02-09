package org.phyloviz.pwp.http.models.datasets.get_dataset;

import org.phyloviz.pwp.http.models.datasets.DatasetModel;
import org.phyloviz.pwp.service.dtos.dataset.FullDatasetInfo;

public class GetDatasetOutputModel extends DatasetModel {
    public GetDatasetOutputModel(FullDatasetInfo fullDatasetInfo) {
        super(fullDatasetInfo);
    }
}
