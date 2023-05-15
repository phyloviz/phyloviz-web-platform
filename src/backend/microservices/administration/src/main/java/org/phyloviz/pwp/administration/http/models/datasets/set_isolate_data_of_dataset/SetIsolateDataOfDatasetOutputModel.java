package org.phyloviz.pwp.administration.http.models.datasets.set_isolate_data_of_dataset;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.dataset.SetIsolateDataOfDatasetOutput;

@Data
public class SetIsolateDataOfDatasetOutputModel {
    private String isolateDataId;
    private String isolateDataKey;

    public SetIsolateDataOfDatasetOutputModel(SetIsolateDataOfDatasetOutput setIsolateDataOfDatasetOutput) {
        this.isolateDataId = String.format("Set to '%s'", setIsolateDataOfDatasetOutput.getIsolateDataId());
        this.isolateDataKey = String.format("Set to '%s'", setIsolateDataOfDatasetOutput.getIsolateDataKey());
    }
}
