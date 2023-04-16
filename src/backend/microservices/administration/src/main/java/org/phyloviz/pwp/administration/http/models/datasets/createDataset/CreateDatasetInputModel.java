package org.phyloviz.pwp.administration.http.models.datasets.createDataset;

import lombok.Data;

@Data
public class CreateDatasetInputModel {
    private String name;
    private String description;
    private String typingDataId;
    private String isolateDataId;
}
