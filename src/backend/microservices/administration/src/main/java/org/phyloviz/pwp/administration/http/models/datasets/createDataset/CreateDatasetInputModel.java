package org.phyloviz.pwp.administration.http.models.datasets.createDataset;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.datasets.createDataset.CreateDatasetInputDTO;
import org.phyloviz.pwp.shared.domain.User;

@Data
public class CreateDatasetInputModel {
    private String name;
    private String description;
    private String typingDataId;
    private String isolateDataId;

    public CreateDatasetInputDTO toDTO(String projectId, User user) {
        return new CreateDatasetInputDTO(name, description, typingDataId, isolateDataId, projectId, user.toDTO());
    }
}
