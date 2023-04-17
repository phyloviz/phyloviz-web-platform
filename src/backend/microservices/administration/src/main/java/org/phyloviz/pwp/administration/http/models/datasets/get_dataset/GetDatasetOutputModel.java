package org.phyloviz.pwp.administration.http.models.datasets.get_dataset;

import org.phyloviz.pwp.administration.http.models.datasets.DatasetModel;
import org.phyloviz.pwp.shared.service.dtos.dataset.FullDatasetInfo;

public class GetDatasetOutputModel extends DatasetModel {
    public GetDatasetOutputModel(FullDatasetInfo fullDatasetInfo) {
        super(fullDatasetInfo);
    }
}
